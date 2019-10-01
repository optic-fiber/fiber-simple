package com.cheroliv.fiber.config

import com.cheroliv.repository.ExtendedRepositoryImpl
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@Configuration
@EnableJpaRepositories(
        basePackages = ["com.cheroliv.fiber.inter.repository",
                "com.cheroliv.repository"],
        repositoryBaseClass = ExtendedRepositoryImpl)
class ExtendedRepositoryConfig {
}