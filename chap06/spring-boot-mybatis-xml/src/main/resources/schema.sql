DROP TABLE IF EXISTS `student`;
CREATE TABLE `student`
(
    `id`        bigint(20)  NOT NULL AUTO_INCREMENT,
    `name`      varchar(255) NOT NULL,
    `age`       int(10) NOT NULL,
    create_time datetime,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;
