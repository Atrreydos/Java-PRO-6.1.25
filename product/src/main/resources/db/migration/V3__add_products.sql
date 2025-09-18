create table if not exists products
(
    id       bigserial primary key,
    account_num varchar(50),
    balance numeric(18, 2),
    type varchar(30),
    user_id bigint references users(id)
);