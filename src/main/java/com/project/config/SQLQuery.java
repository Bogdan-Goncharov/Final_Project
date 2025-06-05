package com.project.config;





public interface SQLQuery {
    String GET_BY_USER_ID = "SELECT p FROM PlayerStats p WHERE p.user.userId = :userId";
}
