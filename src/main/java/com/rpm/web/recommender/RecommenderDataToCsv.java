package com.rpm.web.recommender;

import com.rpm.web.proxy.Trunk;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RecommenderDataToCsv {
    @Autowired
    Trunk<String> trunk;

    public Map<String, Object>  fileWriter ( ) throws IOException {

        Map<String, Object> resultMap = new HashMap<>();
        Map<String, Object> vlaueMap = new HashMap<>();
        List<String> xSubject = new ArrayList<>();
        List<Object> yValueTmp = new ArrayList<>();
        List<Object> yValue = new ArrayList<>();

        String filePath = "C:%sSTSWorkspace%sTeamRPM%scsv%s%s.csv";
        String fileName = "recommender";
        String makePath = String.format(
                filePath,
                File.separator,
                File.separator,
                File.separator,
                File.separator,
                fileName);
        String text = "파일 내용";    // 파일 내용에서 각 셀은 "," 로 구분해 주고 다음 행 입력 시 "\n" 로 구분

        File folder = new File(filePath);

        if (!folder.exists()) {
            folder.mkdirs();    // 폴더가 없을경우 폴더 생성
        }

        BufferedWriter bufferedWriter = null;
        try {
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(makePath), "MS949"));
            bufferedWriter.write(text);
        } catch (IOException e) {
            throw e;
        } finally {
            bufferedWriter.close();
        }

        return resultMap;
    }

}
