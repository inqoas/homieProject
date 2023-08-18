package tw.idv.tibame.tha102.web.announcement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tw.idv.tibame.tha102.web.announcement.service.AnnouncementService;
import tw.idv.tibame.tha102.web.announcement.vo.Announcement;

import java.util.List;

@RestController
@RequestMapping("/announcement")
public class AnnouncementController {
    private final AnnouncementService announcementService;

    @Autowired
    AnnouncementController(AnnouncementService announcementService){
        this.announcementService = announcementService;
    }

    @GetMapping
    public List<Announcement> getAllAnnouncement(){
        return announcementService.getAllAnnouncement();
    }

    @PutMapping("/{articleId}")
    public Announcement updateAnnouncement(@PathVariable("articleId") Integer articleId, @RequestBody Announcement updatedAnnouncement){
        return announcementService.updateAnnouncement(articleId, updatedAnnouncement);
    }

    @DeleteMapping("/{articleId}")
    public void deleteAnnouncement(@PathVariable("articleId") Integer Id){
        System.out.println("Request to delete article with ID: " + Id);
        announcementService.deleteAnnouncementById(Id);
    }

    @PostMapping
    public Announcement createAnnouncement(@RequestBody Announcement announcement){
        return announcementService.createAnnouncement(announcement);
    }
}
