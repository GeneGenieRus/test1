create table gold_audit(
                     user_id long,
                     clan_id long not null,
                     income_amount integer not null,
                     total integer not null,
                     reason varchar,
                     date_time_updated timestamp not null
);