    package jpahello;

    import jakarta.persistence.EntityManager;
    import jakarta.persistence.EntityManagerFactory;
    import jakarta.persistence.EntityTransaction;
    import jakarta.persistence.Persistence;

    import java.util.List;

    public class JpaMain {
        public static void main(String[] args) {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
            EntityManager em = emf.createEntityManager();

            EntityTransaction tx = em.getTransaction();
            tx.begin();
            try {
                Team team = new Team();
                team.setName("TeamA");
                em.persist(team);

                Member member = new Member();
                member.setUsername("member1");
//                member.changeTeam(team);
                em.persist(member);

                team.addMember(member);

//                em.flush();
//                em.clear();

                Team findTeam = em.find(Team.class, team.getId());
                List<Member> members = findTeam.getMembers();

                System.out.println("==================");
                System.out.println("members = " + findTeam);
                System.out.println("==================");

                tx.commit();
            } catch (Exception e) {
                tx.rollback();
            } finally {
                em.close();
            }
            emf.close();
        }
    }
