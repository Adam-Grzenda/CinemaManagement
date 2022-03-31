package pl.put.CinemaManagement.file.s3;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import pl.put.CinemaManagement.file.FileDetails;
import pl.put.CinemaManagement.file.FileList;

import java.util.List;

@Getter
@Setter
public class S3FileList extends FileList {

    private boolean isTruncated;
    private String continuationToken;

    @Builder
    public S3FileList(List<FileDetails> files, boolean isTruncated, String continuationToken) {
        super(files);
        this.isTruncated = isTruncated;
        this.continuationToken = continuationToken;
    }

}
