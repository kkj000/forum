package myProj.forum.service;

import lombok.RequiredArgsConstructor;
import myProj.forum.domain.File;
import myProj.forum.domain.UploadFile;
import myProj.forum.repository.FileMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class FileService {

    private final FileMapper fileMapper;

    @Value("${file.dir}")
    private String fileDir;

    public String getFullPath(String filename) {
        return fileDir + filename;
    }


    public void save(Long postId, UploadFile uploadFile){
       File file = new File();
       file.setPostId(postId);
       file.setOriginalFilename(uploadFile.getUploadFilename());
       file.setStoredFilename(uploadFile.getStoreFilename());
       fileMapper.save(file);
    }

    public File findByPostId(Long postId){
       return fileMapper.findByPostId(postId);
    }

    public UploadFile storeFile(MultipartFile multipartFile) throws IOException {
        String originalFilename = multipartFile.getOriginalFilename();
        String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
        String storeFileName = UUID.randomUUID().toString() + extension;

        multipartFile.transferTo(new java.io.File(fileDir+storeFileName));

        return new UploadFile(originalFilename,storeFileName);
    }

    public List<UploadFile> storeFiles(List<MultipartFile> multipartFiles) throws IOException {
       List<UploadFile> storedFiles = new ArrayList<>();
       for(MultipartFile file : multipartFiles){
           storedFiles.add(storeFile(file));
       }
       return storedFiles;
    }
}
