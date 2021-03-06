CREATE TABLE `users` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `phone_number` varchar(255) UNIQUE,
  `email` varchar(255) UNIQUE,
  `username` varchar(255) UNIQUE NOT NULL,
  `password` varchar(255) NOT NULL,
  `photo_url` varchar(500),
  `full_name` varchar(255),
  `bio` varchar(500),
  `birth_date` date NOT NULL,
  `join_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `invitable` boolean NOT NULL DEFAULT true,
  `role` ENUM('ROLE_USER','ROLE_ADMIN') NOT NULL DEFAULT 'ROLE_ADMIN',
  `activation_token` varchar(255) DEFAULT NULL,
  `reset_token` varchar(255 ) DEFAULT NULL,
  `disbale_token` varchar(255) DEFAULT NULL,
  `verification_code` varchar(255) DEFAULT NULL,
  `is_verified` tinyint(1) DEFAULT 'NONE',
);

CREATE TABLE `user_game_preferences` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `game_id` int NOT NULL
);

CREATE TABLE `games` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `name` varchar(255) UNIQUE NOT NULL,
  `photo_url` varchar(500),
  `description` varchar(500)
);

CREATE TABLE `happy_hours` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `badge_id` int NOT NULL,
  `start_date` datetime NOT NULL,
  `end_date` datetime NOT NULL
);

CREATE TABLE `badges` (
    `id` int PRIMARY KEY AUTO_INCREMENT,
    `game_id` int NOT NULL,
    `title` varchar(50) NOT NULL
);

CREATE TABLE `badge_ships` (
    `id` int PRIMARY KEY AUTO_INCREMENT,
    `badge_id` int NOT NULL,
    `user_id` int NOT NULL
);

CREATE TABLE `teams` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `admin_id` int NOT NULL,
  `photo_url` varchar(500),
  `name` varchar(255) NOT NULL,
  `description` varchar(500),
  `game_id` int,
  `team_size` int NOT NULL DEFAULT 1,
  `requestable` boolean NOT NULL DEFAULT true,
  `invitable` boolean NOT NULL DEFAULT true,
  `create_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE `memberships` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `user_id` int,
  `team_id` int NOT NULL,
  `tournament_id` int
);

CREATE TABLE `join_requests` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `user_id` int,
  `team_id` int NOT NULL,
  `tournament_id` int,
  `message` varchar(255),
  `request_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `accepted` boolean,
  `response_date` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `invitation` boolean NOT NULL DEFAULT false
);

CREATE TABLE `tournaments` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `admin_id` int NOT NULL,
  `game_id` int,
  `name` varchar(255) NOT NULL,
  `description` varchar(500),
  `required_teams` int NOT NULL,
  `team_size` int NOT NULL DEFAULT 1,
  `close_requests_date` datetime NOT NULL,
  `approved` boolean NOT NULL DEFAULT false,
  `create_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE `tournament_reports` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `tournament_id` int NOT NULL,
  `report_id` int NOT NULL
);

CREATE TABLE `matches` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `tournament_id` int,
  `start_time` datetime NOT NULL,
  `round` int,
  `team1_id` int NOT NULL,
  `team2_id` int NOT NULL,
  `winner_team_id` int
);

CREATE TABLE `market_items` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `store_id` int NOT NULL,
  `title` varchar(255) NOT NULL,
  `description` varchar(500),
  `sold` boolean DEFAULT false,
  `post_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE `market_item_reports` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `market_item_id` int NOT NULL,
  `report_id` int NOT NULL
);

CREATE TABLE `stores` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `owner_id` int NOT NULL,
  `game_id` int,
  `name` varchar(255) NOT NULL
);

CREATE TABLE `store_reports` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `store_id` int NOT NULL,
  `report_id` int NOT NULL
);

CREATE TABLE `posts` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `poster_id` int NOT NULL,
  `resolved` boolean NOT NULL DEFAULT false,
  `title` varchar(500) NOT NULL,
  `content` varchar(2555) NOT NULL,
  `tags` varchar(255),
  `post_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE `post_reports` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `post_id` int NOT NULL,
  `report_id` int NOT NULL
);

CREATE TABLE `comments` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `post_id` int NOT NULL,
  `commenter_id` int NOT NULL,
  `comment_body` varchar(1000) NOT NULL,
  `comment_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE `user_likes_dislikes` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `store_id` int,
  `post_id` int,
  `comment_id` int,
  `like` boolean NOT NULL 
);

CREATE TABLE `reports` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `reporter_id` int NOT NULL,
  `subject` ENUM('sensitive','score_manipulation') NOT NULL,
  `head` varchar(255) NOT NULL,
  `body` varchar(1000),
  `report_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP
);

ALTER TABLE `users` ADD CHECK (`phone_number` IS NOT NULL OR `email` IS NOT NULL);

ALTER TABLE `user_game_preferences` ADD FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);

ALTER TABLE `user_game_preferences` ADD FOREIGN KEY (`game_id`) REFERENCES `games` (`id`);

ALTER TABLE `happy_hours` ADD FOREIGN KEY (`badge_id`) REFERENCES `badges` (`id`);

ALTER TABLE `happy_hours` ADD CHECK (`start_date` < `end_date`);

ALTER TABLE `badges` ADD FOREIGN KEY (`game_id`) REFERENCES `games` (`id`);

ALTER TABLE `badge_ships` ADD FOREIGN KEY (`badge_id`) REFERENCES `badges` (`id`);

ALTER TABLE `badge_ships` ADD FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);

ALTER TABLE `teams` ADD FOREIGN KEY (`admin_id`) REFERENCES `users` (`id`);

ALTER TABLE `teams` ADD FOREIGN KEY (`game_id`) REFERENCES `games` (`id`);

ALTER TABLE `memberships` ADD FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);

ALTER TABLE `memberships` ADD FOREIGN KEY (`team_id`) REFERENCES `teams` (`id`);

ALTER TABLE `memberships` ADD FOREIGN KEY (`tournament_id`) REFERENCES `tournaments` (`id`);

ALTER TABLE `memberships` ADD CHECK ((user_id IS NOT NULL OR tournament_id IS NOT NULL) AND NOT (user_id IS NOT NULL AND tournament_id IS NOT NULL));

ALTER TABLE `join_requests` ADD FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);

ALTER TABLE `join_requests` ADD FOREIGN KEY (`team_id`) REFERENCES `teams` (`id`);

ALTER TABLE `join_requests` ADD FOREIGN KEY (`tournament_id`) REFERENCES `tournaments` (`id`);

ALTER TABLE `join_requests` ADD CHECK ((user_id IS NOT NULL OR tournament_id IS NOT NULL) AND NOT (user_id IS NOT NULL AND tournament_id IS NOT NULL));

ALTER TABLE `tournaments` ADD FOREIGN KEY (`admin_id`) REFERENCES `users` (`id`);

ALTER TABLE `tournaments` ADD FOREIGN KEY (`game_id`) REFERENCES `games` (`id`);

ALTER TABLE `tournaments` ADD CHECK (`required_teams` IN (4, 8, 16, 32));

ALTER TABLE `tournament_reports` ADD FOREIGN KEY (`tournament_id`) REFERENCES `tournaments` (`id`);

ALTER TABLE `tournament_reports` ADD FOREIGN KEY (`report_id`) REFERENCES `reports` (`id`);

ALTER TABLE `matches` ADD FOREIGN KEY (`tournament_id`) REFERENCES `tournaments` (`id`);

ALTER TABLE `matches` ADD FOREIGN KEY (`team1_id`) REFERENCES `teams` (`id`);

ALTER TABLE `matches` ADD FOREIGN KEY (`team2_id`) REFERENCES `teams` (`id`);

ALTER TABLE `matches` ADD FOREIGN KEY (`winner_team_id`) REFERENCES `teams` (`id`);

ALTER TABLE `matches` ADD CHECK (`winner_team_id` = `team1_id` or `winner_team_id` = `team2_id`);

ALTER TABLE `market_items` ADD FOREIGN KEY (`store_id`) REFERENCES `stores` (`id`);

ALTER TABLE `market_item_reports` ADD FOREIGN KEY (`market_item_id`) REFERENCES `market_items` (`id`);

ALTER TABLE `market_item_reports` ADD FOREIGN KEY (`report_id`) REFERENCES `reports` (`id`);

ALTER TABLE `stores` ADD FOREIGN KEY (`owner_id`) REFERENCES `users` (`id`);

ALTER TABLE `stores` ADD FOREIGN KEY (`game_id`) REFERENCES `games` (`id`);

ALTER TABLE `store_reports` ADD FOREIGN KEY (`store_id`) REFERENCES `stores` (`id`);

ALTER TABLE `store_reports` ADD FOREIGN KEY (`report_id`) REFERENCES `reports` (`id`);

ALTER TABLE `posts` ADD FOREIGN KEY (`poster_id`) REFERENCES `users` (`id`);

ALTER TABLE `post_reports` ADD FOREIGN KEY (`post_id`) REFERENCES `posts` (`id`);

ALTER TABLE `post_reports` ADD FOREIGN KEY (`report_id`) REFERENCES `reports` (`id`);

ALTER TABLE `comments` ADD FOREIGN KEY (`post_id`) REFERENCES `posts` (`id`);

ALTER TABLE `comments` ADD FOREIGN KEY (`commenter_id`) REFERENCES `users` (`id`);

ALTER TABLE `user_likes_dislikes` ADD FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);

ALTER TABLE `user_likes_dislikes` ADD FOREIGN KEY (`store_id`) REFERENCES `stores` (`id`);

ALTER TABLE `user_likes_dislikes` ADD FOREIGN KEY (`post_id`) REFERENCES `posts` (`id`);

ALTER TABLE `user_likes_dislikes` ADD FOREIGN KEY (`comment_id`) REFERENCES `comments` (`id`);

ALTER TABLE `user_likes_dislikes` ADD CHECK (`store_id` IS NOT NULL OR `post_id` IS NOT NULL OR `comment_id` IS NOT NULL AND NOT (`store_id` IS NOT NULL AND (`post_id` IS NOT NULL OR `comment_id` IS NOT NULL) OR `post_id` IS NOT NULL AND (`store_id` IS NOT NULL OR `comment_id` IS NOT NULL)  OR `comment_id` IS NOT NULL AND (`store_id` IS NOT NULL OR `post_id` IS NOT NULL)));

ALTER TABLE `reports` ADD FOREIGN KEY (`reporter_id`) REFERENCES `users` (`id`);