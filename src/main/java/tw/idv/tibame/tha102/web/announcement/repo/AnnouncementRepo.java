package tw.idv.tibame.tha102.web.announcement.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tw.idv.tibame.tha102.web.announcement.vo.Announcement;

@Repository
public interface AnnouncementRepo extends JpaRepository<Announcement, Integer> {
}
