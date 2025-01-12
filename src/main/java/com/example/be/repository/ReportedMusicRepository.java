package com.example.be.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.be.model.ReportMusic;

@Repository
public interface ReportedMusicRepository extends JpaRepository<ReportMusic, Long> {
        @Query(value = " select rm.id , rm.music_id , m.music_name , " +
                        " u.name as artist , " +
                        " ur.name as reported_name , " +
                        " rm.day , " +
                        " rm.report_content " +
                        " from report_music rm " +
                        " join music m on m.id = rm.music_id " +
                        " join user ur on ur.id = m.artist_id " +
                        " join user u on u.id = m.artist_id " +
                        " where rm.hidden='0' "+
                        "order by rm.day desc", nativeQuery = true)
        List<Object[]> findAllReportedMuisc();

        

        Optional<ReportMusic> findById(int reportId);
}
