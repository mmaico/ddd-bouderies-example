package com.crm.register.view.dto;


import com.crm.infrastructure.entity.AppFile;
import com.crm.infrastructure.entity.builders.AppFileBuilder;
import com.crm.infrastructure.helpers.CollectionsHelper;
import com.crm.infrastructure.helpers.MultipartFileUtils;
import com.google.common.collect.Lists;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public class UploadFileVO {

    private List<MultipartFile> files;

    public List<MultipartFile> getFiles() {
        return files;
    }

    public void setFiles(List<MultipartFile> files) {
        this.files = files;
    }

    public List<AppFile> getAppFiles() throws IOException {

        List<AppFile> items = Lists.newArrayList();

        for (MultipartFile multipart : CollectionsHelper.safeIterable(files)) {

            AppFile appFile = new AppFileBuilder().withFile(MultipartFileUtils.safe(multipart).getBytes())
                    .withMimeType(MultipartFileUtils.safe(multipart).getContentType())
                    .withOriginalName(MultipartFileUtils.safe(multipart).getOriginalFilename())
                    .withSize(MultipartFileUtils.safe(multipart).getSize())
                    .build();

            if (!appFile.isValid()) {
                items.add(appFile);
            }
        }

        return items;
    }
}
