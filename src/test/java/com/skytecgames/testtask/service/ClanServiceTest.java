package com.skytecgames.testtask.service;

import com.skytecgames.testtask.model.UpdateGoldRequest;
import com.skytecgames.testtask.repository.GoldAuditRepository;
import com.skytecgames.testtask.service.impl.AsyncAuditService;
import com.skytecgames.testtask.service.impl.ClanServiceImpl;
import com.skytecgames.testtask.service.utils.DbGoldAuditTestHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;


class ClanServiceTest extends BaseTest {

    public static final int TEST_THREAD_POOL_SIZE = 20;
    public static final int UPDATE_GOLD_REQUESTS_SIZE = 100000;
    public static final int MAX_INCOME_VALUE = 500;

    public static final int AUDIT_THREAD_POOL_SIZE = 3;

    public static final long CLAN_ID = 1L;
    public static final long USER_ID = 1L;

    private ExecutorService testExecutorService;
    private ExecutorService auditExecutorService;
    private ClanService clanService;

    @BeforeEach
    public void before() {
        testExecutorService = Executors.newFixedThreadPool(TEST_THREAD_POOL_SIZE);
        auditExecutorService = Executors.newFixedThreadPool(AUDIT_THREAD_POOL_SIZE);
        clanService = new ClanServiceImpl(new AsyncAuditService(auditExecutorService, new GoldAuditRepository()));
    }

    @Test
    void updateGoldMultithreadingTest() throws InterruptedException {
        List<Integer> amounts = generateRandomInts();
        int expectedTotal = amounts.stream().mapToInt(Integer::intValue).sum();

        System.out.println("amounts list(length = " + amounts.size() + "): " + amounts.toString().substring(0, 70) + "...]");
        System.out.println("expected total amount: " + expectedTotal);

        long startTime = System.currentTimeMillis();
        for (Integer amount : amounts) {
            testExecutorService.execute(() -> clanService.updateGold(
                    new UpdateGoldRequest(USER_ID, CLAN_ID, amount, "test")));
        }
        awaitThreadsForComplete();
        System.out.println("Test duration: " + (System.currentTimeMillis() - startTime) + " ms");

        int resultTotal = clanService.get(1).getGold().get();
        assertEquals(expectedTotal, resultTotal);
        assertEquals(expectedTotal, DbGoldAuditTestHelper.getSumOfAmounts());
        assertEquals(amounts.size(), DbGoldAuditTestHelper.getCount());
    }

    private void awaitThreadsForComplete() throws InterruptedException {
        testExecutorService.shutdown();
        testExecutorService.awaitTermination(100, TimeUnit.SECONDS);

        auditExecutorService.shutdown();
        auditExecutorService.awaitTermination(100, TimeUnit.SECONDS);
    }

    private List<Integer> generateRandomInts() {
        Random random = new Random(new Date().getTime());

        return Stream.generate(
                () -> random.nextInt(MAX_INCOME_VALUE) * (random.nextBoolean() ? 1 : -1))
                .limit(UPDATE_GOLD_REQUESTS_SIZE)
                .collect(Collectors.toList());
    }
}