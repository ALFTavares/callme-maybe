package org.academiadecodigo.hackaton.server;

import org.hibernate.HibernateException;
import org.academiadecodigo.hackaton.shared.Score;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.List;

/**
 * Created by bob on 13-07-2017.
 */
public class PersistenceHandler {
    private Server server;

    public PersistenceHandler(Server server) {
        this.server = server;
    }


    public void updateScore(Score score) {

        try {

            Integer newscore = score.getScore();
            Score oldscore = findByName(score.getName());

            Session session = HibernateSessionManager.getInstance().beginTransaction();
            System.out.println("oldscore: " + oldscore);
            if (oldscore == null) {
                session.save(score);
            } else if (newscore > oldscore.getScore()) {
                oldscore.setScore(newscore);
                session.update(oldscore);
                //show user "you got a new high score!"
            }
            HibernateSessionManager.getInstance().commitTransaction();
        } catch (HibernateException e) {
            HibernateSessionManager.getInstance().rollBackTransaction();
        }
    }


    public Score findByName(String usernametofind) {
        Score score = null;

        try {
            Session session = HibernateSessionManager.getInstance().beginTransaction();

            Query query = session.createQuery("from Score where name = :username");
            query.setString("username", usernametofind);
            score = (Score) query.uniqueResult();


            HibernateSessionManager.getInstance().commitTransaction();
        } catch (HibernateException ex) {
            HibernateSessionManager.getInstance().rollBackTransaction();
        }
        return score;
    }

    public boolean isNewHighScoreForUser(Score score) {
        boolean ishigh = false;
        try {
            Session session = HibernateSessionManager.getInstance().beginTransaction();

            Query query = session.createQuery("from Score where name = :username");
            query.setString("username", score.getName());
            Score oldscore = (Score) query.uniqueResult();
            if (oldscore != null) {
                if (score.getScore() > oldscore.getScore()) {
                    ishigh = true;
                } else {
                    ishigh = false;
                }
            } else {
                ishigh = true;
            }

            HibernateSessionManager.getInstance().commitTransaction();
        } catch (HibernateException ex) {
            HibernateSessionManager.getInstance().rollBackTransaction();
        }
        return ishigh;
    }

    public List<Score> getHighScores() {
        List<Score> list = null;
        try {
            Session session = HibernateSessionManager.getInstance().beginTransaction();
            Query query = session.createQuery("FROM Score ORDER BY score DESC");
            list = query.list();

            HibernateSessionManager.getInstance().commitTransaction();
        } catch (HibernateException ex) {
            HibernateSessionManager.getInstance().rollBackTransaction();
        }
        return list;
    }

    //public User findByEmail(String email);
    //public String getName() { return UserService.class.getSimpleName(); }
}
