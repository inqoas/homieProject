package tw.idv.tibame.tha102.web.announcement.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tw.idv.tibame.tha102.web.announcement.repo.AnnouncementRepo;
import tw.idv.tibame.tha102.web.announcement.vo.Announcement;

import java.util.List;

@Service
public class AnnouncementService {
    private AnnouncementRepo announcementRepo;

    @Autowired
    public AnnouncementService(AnnouncementRepo announcementRepo){
        this.announcementRepo = announcementRepo;
    }

    // 查所有
    public List<Announcement> getAllAnnouncement(){
        return announcementRepo.findAll();
    }
    // 更新
    public Announcement updateAnnouncement(Integer id, Announcement updatedAnnouncement){
        return announcementRepo.findById(id).map(existingAnnouncement ->{
            existingAnnouncement.setEmpId(updatedAnnouncement.getEmpId());
            existingAnnouncement.setUploadDate(updatedAnnouncement.getUploadDate());
            existingAnnouncement.setArticleTitle(updatedAnnouncement.getArticleTitle());
            existingAnnouncement.setArticleContent(updatedAnnouncement.getArticleContent());
            return announcementRepo.save(existingAnnouncement);
        }).orElseThrow(() -> new EntityNotFoundException("Announcement with id" + id + "not found"));
    }
    // 刪除
    public void deleteAnnouncementById(Integer id){
        announcementRepo.deleteById(id);
    }
    //新增
    public Announcement createAnnouncement(Announcement announcement){
        return announcementRepo.save(announcement);
    }
}
