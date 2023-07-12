    package jpahello;

    import jakarta.persistence.*;
    import org.hibernate.Hibernate;

    import java.time.LocalDateTime;
    import java.util.List;

    public class JpaMain {
        public static void main(String[] args) {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
            EntityManager em = emf.createEntityManager();

            EntityTransaction tx = em.getTransaction();
            tx.begin();
            try {
                Member member1 = new Member();
                member1.setUsername("member1");
                em.persist(member1);

                Member member2 = new Member();
                member2.setUsername("member2");
                em.persist(member2);

                em.flush();
                em.clear();

                Member refMember = em.getReference(Member.class, member1.getId());
                System.out.println("refMember = " + refMember.getClass());
                Hibernate.initialize(refMember);    //강제 초기화


                tx.commit();
            } catch (Exception e) {
                e.printStackTrace();
                tx.rollback();
            } finally {
                em.close();
            }
            emf.close();
        }

        private static void printMember(Member member) {
            String username = member.getUsername();
            System.out.println("username = " + username);
        }

        private static void printMemberAndTeam(Member member) {
            String username = member.getUsername();
            System.out.println("username = " + username);

            Team team = member.getTeam();
            System.out.println("team = " + team.getName());
        }
    }
