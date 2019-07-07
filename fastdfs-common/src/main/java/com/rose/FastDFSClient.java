package com.rose;

import org.csource.common.MyException;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.*;
import org.springframework.core.io.ClassPathResource;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class FastDFSClient {
    private static TrackerClient trackerClient;
    private static StorageServer storageServer;
    private static TrackerServer trackerServer;

    static {

        try {
            String path = new ClassPathResource("fdfs_client.conf").getFile().getAbsolutePath();
            ClientGlobal.init(path);
            trackerClient = new TrackerClient();
            trackerServer = trackerClient.getConnection();
            storageServer = trackerClient.getStoreStorage(storageServer);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (MyException e) {
            e.printStackTrace();
        }

    }

    public static String[]  uploadFile(FastDFSFile file) {

        NameValuePair pair = new NameValuePair("author", file.getAuthor());
        NameValuePair[] metadata = {pair};
        StorageClient storageClient = new StorageClient(trackerServer, storageServer);
        String[] uploadResult = null;
        try {
            uploadResult = storageClient.upload_file(file.getContent(), file.getExt(), metadata);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (MyException e) {
            e.printStackTrace();
        }
        return uploadResult;
    }

    public static FileInfo getFile(String groupName, String remoteFileName) {

        StorageClient storageClient = new StorageClient(trackerServer, storageServer);
        try {
            return storageClient.get_file_info(groupName, remoteFileName);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (MyException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static InputStream downloadFile(String groupName, String remoteFileName) {
        StorageClient storageClient = new StorageClient(trackerServer, storageServer);
        try {
            byte[] bytes = storageClient.download_file(groupName, remoteFileName);
            ByteArrayInputStream in = new ByteArrayInputStream(bytes);
            return in;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (MyException e) {
            e.printStackTrace();
        }
        return null;
    }
}
