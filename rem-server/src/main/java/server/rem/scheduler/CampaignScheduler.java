package server.rem.scheduler;

// import java.time.LocalDateTime;
// import java.util.List;

// import org.springframework.scheduling.annotation.Scheduled;
// import org.springframework.stereotype.Component;

// import jakarta.transaction.Transactional;
// import lombok.RequiredArgsConstructor;
// import lombok.extern.slf4j.Slf4j;
// import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;
// import server.rem.entities.Campaign;
// import server.rem.enums.CampaignStatus;
// import server.rem.repositories.CampaignRepository;
// import server.rem.services.CampaignService;

// @Slf4j
// @Component
// @RequiredArgsConstructor
// public class CampaignScheduler {
//     private final CampaignRepository campaignRepository;
//     private final CampaignService campaignService;

//     @Scheduled(fixedDelay = 60_000) // runs every 60 seconds
//     @SchedulerLock(name = "processDueCampaigns", lockAtMostFor = "5m")
//     @Transactional
//     public void processDueCampaigns() {
//         List<Campaign> dueCampaigns = campaignRepository.findDueCampaigns(LocalDateTime.now());
        
//         for (Campaign campaign : dueCampaigns) {
//             try {
//                 // Lock it immediately to prevent race condition
//                 campaign.setStatus(CampaignStatus.PROCESSING);
//                 campaignRepository.save(campaign);

//                 campaignService.sendCampaign(campaign);

//                 campaign.setStatus(CampaignStatus.SENT);
//             } catch (Exception e) {
//                 log.error("Failed to send campaign {}", campaign.getId(), e);
//                 campaign.setStatus(CampaignStatus.FAILED);
//             } finally {
//                 campaignRepository.save(campaign);
//             }
//         }
//     }
// }

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.springframework.stereotype.Component;
import server.rem.entities.Campaign;
import server.rem.jobs.CampaignJob;

import java.util.Date;

@Slf4j
@Component
@RequiredArgsConstructor
public class CampaignScheduler {
    private final Scheduler scheduler;

    public void scheduleCampaign(Campaign campaign) {
        try {
            JobDetail jobDetail = buildJobDetail(campaign);
            Trigger trigger = buildTrigger(campaign);
            scheduler.scheduleJob(jobDetail, trigger);
            log.info("Scheduled campaign {} at {}", campaign.getId(), campaign.getScheduleAt());
        } catch (SchedulerException e) {
            log.error("Failed to schedule campaign {}", campaign.getId(), e);
            throw new RuntimeException("Could not schedule campaign", e);
        }
    }

    public void cancelCampaign(String campaignId) {
        try {
            scheduler.deleteJob(jobKey(campaignId));
            log.info("Cancelled scheduled campaign {}", campaignId);
        } catch (SchedulerException e) {
            log.error("Failed to cancel campaign {}", campaignId, e);
        }
    }

    public void rescheduleCampaign(Campaign campaign) {
        cancelCampaign(campaign.getId());
        scheduleCampaign(campaign);
    }

    // --- helper functions ---
    private JobDetail buildJobDetail(Campaign campaign) {
        JobDataMap data = new JobDataMap();
        data.put("campaignId", campaign.getId());
        return JobBuilder.newJob(CampaignJob.class)
                .withIdentity(jobKey(campaign.getId()))
                .withDescription("Send campaign: " + campaign.getName())
                .usingJobData(data)
                .storeDurably()
                .build();
    }

    private Trigger buildTrigger(Campaign campaign) {
        Date fireAt = Date.from(campaign.getScheduleAt());
        
        return TriggerBuilder.newTrigger()
                .withIdentity("trigger_campaign_" + campaign.getId())
                .startAt(fireAt)
                .withSchedule(SimpleScheduleBuilder.simpleSchedule().withMisfireHandlingInstructionFireNow())
                // withMisfireHandlingInstructionFireNow // if app was down when trigger was supposed to fire, it fires immediately on restart
                .build();
    }

    private JobKey jobKey(String campaignId) {
        return JobKey.jobKey("campaign_" + campaignId, "campaigns");
    }
}