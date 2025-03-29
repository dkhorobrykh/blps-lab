package ru.itmo.blps.labs.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itmo.blps.labs.domain.Announcement;
import ru.itmo.blps.labs.domain.AnnouncementStatus;

@Repository
public interface AnnouncementRepository extends JpaRepository<Announcement, Long> {

    List<Announcement> findAllByGroupId(Long groupId);

    List<Announcement> findAllByStatusOrderById(AnnouncementStatus status);
}