package ru.itmo.blps.labs.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itmo.blps.labs.domain.Community;

@Repository
public interface CommunityRepository extends JpaRepository<Community, Long> {

}