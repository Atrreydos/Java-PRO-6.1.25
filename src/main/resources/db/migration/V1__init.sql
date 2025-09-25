create table if not exists limits
(
    id        bigserial primary key,
    client_id bigint unique not null,
    amount    numeric(18, 2) not null
);
