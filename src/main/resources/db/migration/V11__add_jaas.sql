create table s367595.blps_role (
    id bigserial primary key not null ,
    name varchar(255) not null unique
);

create table s367595.blps_privilege (
    id bigserial primary key not null ,
    name varchar(255) not null unique
);

create table s367595.blps_user_role (
    user_id bigint references s367595.blps_user(id) not null,
    role_id bigint references s367595.blps_role(id) not null,

    primary key (user_id, role_id)
);

create table s367595.blps_role_privileges (
    role_id bigint references s367595.blps_role(id) not null,
    privilege_id bigint references s367595.blps_privilege(id) not null,

    primary key (role_id, privilege_id)
);