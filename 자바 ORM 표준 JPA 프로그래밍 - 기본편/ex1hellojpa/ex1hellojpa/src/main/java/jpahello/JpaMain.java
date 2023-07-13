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

                Team team = new Team();
                team.setName("teamA");
                System.out.println(team.getName());
                em.persist(team);

                Member member1 = new Member();
                member1.setUsername("member1");
                member1.setTeam(team);
                em.persist(member1);


                em.flush();
                em.clear();

//                Member m = em.find(Member.class, member1.getId());

                em.createQuery("select m from Member m join fetch  m.team", Member.class).getResultList();


                tx.commit();
            } catch (Exception e) {
                tx.rollback();
                e.printStackTrace();
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
