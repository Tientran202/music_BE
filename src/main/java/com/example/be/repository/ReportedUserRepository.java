package com.example.be.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.be.model.ReportedUser;

@Repository
public interface ReportedUserRepository extends JpaRepository<ReportedUser, Long> {

        @Query(value = "select u.name as user, " +
                        "u.id as user_id, " +
                        "ur.name as report_user, " +
                        "ur.id as annunciator, " +
                        "r.report_content as report_content, " +
                        "r.day " +
                        "from reported_user r " +
                        "join user u on r.user_id = u.id " +
                        "join user ur on r.annunciator_id = ur.id "+
                        "WHERE r.hidden = 0", nativeQuery = true)
        List<Object[]> findAllReportedUsers();

        @Query(value = "select u.name as userName, " +
                        "r.user_id as userId, " +
                        "ur.name as reportUserName, " +
                        "r.annunciator_id as annunciatorId, " +
                        "r.day as reporting_time, " +
                        "r.report_content as content, " +
                        "r.hidden_time " +
                        "from reported_user r " +
                        "join user u  on r.user_id = u.id " +
                        "join user ur  on r.annunciator_id = ur.id " +
                        "WHERE r.hidden = 1", nativeQuery = true)
        List<Object[]> findAllHiddenUsers();

}
