insert into user(`id`, `name`, `email`, `created_at`, `updated_at`) values(1, 'martin', 'martin@naver.com', now(), now());

insert into user(`id`, `name`, `email`, `created_at`, `updated_at`) values(2, 'lee', 'lee@naver.com', now(), now());

insert into user(`id`, `name`, `email`, `created_at`, `updated_at`) values(3, 'kim', 'kim@naver.com', now(), now());

insert into user(`id`, `name`, `email`, `created_at`, `updated_at`) values(4, 'choi', 'choi@naver.com', now(), now());

insert into user(`id`, `name`, `email`, `created_at`, `updated_at`) values(5, 'park', 'park@naver.com', now(), now());

insert into publisher(`id`, `name`) values (1, "패스트캠퍼스")

insert into book(`id`, `name`, `publisher_id`, `deleted`) values (1, 'JPA1', 1, false);

insert into book(`id`, `name`, `publisher_id`, `deleted`) values (2, 'JPA2', 1, false);

insert into book(`id`, `name`, `publisher_id`, `deleted`) values (3, 'JPA3', 1, true);