DROP TABLE IF EXISTS `user`;
DROP TABLE IF EXISTS `role`;
DROP TABLE IF EXISTS `user_role`;
DROP TABLE IF EXISTS `role_permission`;
DROP TABLE IF EXISTS `permission`;

CREATE TABLE `user`
(
    `id`       bigint(11)   NOT NULL AUTO_INCREMENT,
    `username` varchar(255) NOT NULL,
    `password` varchar(255) NOT NULL,
    PRIMARY KEY (`id`)
);
CREATE TABLE `role`
(
    `id`   bigint(11)   NOT NULL AUTO_INCREMENT,
    `name` varchar(255) NOT NULL,
    PRIMARY KEY (`id`)
);
CREATE TABLE `user_role`
(
    `user_id` bigint(11) NOT NULL,
    `role_id` bigint(11) NOT NULL
);
CREATE TABLE `role_permission`
(
    `role_id`       bigint(11) NOT NULL,
    `permission_id` bigint(11) NOT NULL
);
CREATE TABLE `permission`
(
    `id`          bigint(11)   NOT NULL AUTO_INCREMENT,
    `url`         varchar(255) NOT NULL,
    `name`        varchar(255) NOT NULL,
    `description` varchar(255) NULL,
    `pid`         bigint(11)   NOT NULL,
    PRIMARY KEY (`id`)
);
