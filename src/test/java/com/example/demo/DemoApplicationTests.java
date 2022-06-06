package com.example.demo;

import com.example.demo.entity.QTests;
import com.example.demo.entity.Tests;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class DemoApplicationTests {

	@PersistenceContext
	EntityManager em;

	@Test
	void contextLoads() {
		Tests tests = new Tests();
		em.persist(tests);

		JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(em);
		//QTests test = new QTests("t");
		QTests test = QTests.tests;

		Tests fetchOne = jpaQueryFactory
				.selectFrom(test)
				.fetchOne();

		assertThat(fetchOne).isEqualTo(tests);
	}

}
