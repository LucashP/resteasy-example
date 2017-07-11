/*
 * Hibernate, Relational Persistence for Idiomatic Java
 *
 * Copyright (c) 2010, Red Hat Inc. or third-party contributors as
 * indicated by the @author tags or express copyright attribution
 * statements applied by the authors.  All third-party contributions are
 * distributed under license by Red Hat Inc.
 *
 * This copyrighted material is made available to anyone wishing to use, modify,
 * copy, or redistribute it subject to the terms and conditions of the GNU
 * Lesser General Public License, as published by the Free Software Foundation.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Lesser General Public License
 * for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this distribution; if not, write to:
 * Free Software Foundation, Inc.
 * 51 Franklin Street, Fifth Floor
 * Boston, MA  02110-1301  USA
 */
package pl.lucash.resteasy.rubbish;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.hibernate.envers.query.AuditEntity;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class UzytkownikItTest {
    private static SessionFactory sessionFactory;

    @BeforeClass
    public static void setUp() throws Exception {
        String hibernateConfiguration = "it/hibernate.cfg.xml";

        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure(hibernateConfiguration)
                .build();
        try {
            sessionFactory = new Configuration().configure(hibernateConfiguration).buildSessionFactory(registry);
        } catch (Exception e) {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }

    @AfterClass
    public static void tearDown() throws Exception {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }

    @SuppressWarnings({"unchecked", "JUnit4AnnotatedMethodInJUnit3TestCase"})
    @Test
    public void testBasicUsage() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(new Uzytkownik("test1@email.com", "test1@email.com"));
        session.save(new Uzytkownik("test2@email.com", "test2@email.com"));
        session.save(new Uzytkownik("test3@email.com", "test3@email.com"));
        session.save(new Uzytkownik("test4@email.com", "test4@email.com"));
        session.getTransaction().commit();
        session.close();
        session = sessionFactory.openSession();
        session.beginTransaction();
        List result = session.createQuery("from Uzytkownik").list();
        for (Uzytkownik person : (List<Uzytkownik>) result) {
            System.out.println("Uzytkownik (" + person + ") ");
        }
        session.getTransaction().commit();
        session.close();
    }

    @SuppressWarnings("JUnit4AnnotatedMethodInJUnit3TestCase")
    @Ignore
    public void testModify() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Uzytkownik uzytkownik = (Uzytkownik) session.get(Uzytkownik.class, 3);
        uzytkownik.setEmail("test3@email.com");
        session.save(uzytkownik);
        session.getTransaction().commit();
        session.close();
    }

    @SuppressWarnings({"unchecked", "UnnecessaryUnboxing"})
    @Ignore
    public void testAudit() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        List<Uzytkownik> uzytkownicyZRoznymiEmailami = (List<Uzytkownik>) session.createQuery("from Uzytkownik u where u.email != u.nowyEmail").list();
        uzytkownicyZRoznymiEmailami.forEach(uzytkownik -> System.out.println("Uzytkownik (" + uzytkownik + ") "));

        AuditReader reader = AuditReaderFactory.get(session);
        Date date = new Date();
        date.setTime(1499247514133L);

        Number lastDayRevision = reader.getRevisionNumberForDate(date);
        System.out.println(lastDayRevision);

        List<Uzytkownik> filtered = uzytkownicyZRoznymiEmailami.stream().filter(uzytkownik -> {
            Object singleResult = reader.createQuery()
                    .forRevisionsOfEntity(Uzytkownik.class, true, false)
                    .addProjection(AuditEntity.revisionNumber().max())
                    .add(AuditEntity.id().eq(uzytkownik.getId()))
                    .getSingleResult();
            System.out.println(uzytkownik);
            System.out.println("AuditEntity.revisionNumber().max() "+singleResult);
            if (singleResult instanceof Integer) {
                return lastDayRevision.intValue() >= ((Integer) singleResult).intValue();
            }
            return false;
        }).collect(Collectors.toList());

        System.out.println("filtered.size() "+ filtered.size());
        filtered.forEach(uzytkownik -> System.out.println("Uzytkownik (" + uzytkownik + ") "));
        session.getTransaction().commit();
        session.close();
    }
}
