package info.smartkit.saas.steganographyaas.controller;

import com.shekhargulati.reactivex.docker.client.RxDockerClient;
import com.shekhargulati.reactivex.docker.client.representations.DockerVersion;
import info.smartkit.saas.steganographyaas.dto.StegonagraphyInfo;
import info.smartkit.saas.steganographyaas.utils.*;
import io.swagger.annotations.ApiOperation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.im4java.core.ConvertCmd;
import org.im4java.core.IM4JavaException;
import org.im4java.core.IMOperation;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.ws.rs.core.MediaType;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.FilenameUtils;

@RestController
public class StegonagraphyController {

    private static Logger LOG = LogManager.getLogger(StegonagraphyController.class);

    // Enum for image size.
    enum ImageSize {
        ori, sml, ico
    }

    //Tools actually doing steganography
    @RequestMapping(method = RequestMethod.POST, value = "/graphy", consumes = MediaType.MULTIPART_FORM_DATA)
    @ApiOperation(value = "Response a string describing Stegona' picture is successfully graphied or not.", position = 1)
//	@ApiImplicitParams({@ApiImplicitParam(name="Authorization", value="Authorization DESCRIPTION")})
    public @ResponseBody
    StegonagraphyInfo graphing(
            @RequestParam(value = "tool", required = true, defaultValue =
                    "openstego") StegonaGrahpyTools tool,
            @RequestPart(value = "file") @Valid @NotNull @NotBlank MultipartFile secretTxt) {
        // Stegonagraphying:
        //https://github.com/shekhargulati/rx-docker-client
        //Create a new Docker client using DOCKER_HOST and DOCKER_CERT_PATH environment variables
        RxDockerClient client = RxDockerClient.fromDefaultEnv();

        // Getting Docker version
        DockerVersion dockerVersion = client.serverVersion();
        System.out.println(dockerVersion.version()); // 1.8.3
        return null;
    }

    //Tools detecting steganography
    @RequestMapping(method = RequestMethod.POST, value = "/detect", consumes = MediaType.MULTIPART_FORM_DATA)
    @ApiOperation(value = "Response a string describing Stegona' picture is successfully detedcted or not.", position = 3)
//	@ApiImplicitParams({@ApiImplicitParam(name="Authorization", value="Authorization DESCRIPTION")})
    public @ResponseBody
    StegonagraphyInfo detecting(
            @RequestParam(value = "tool", required = true, defaultValue =
                    "zsteg") StegonaDetectTools tool,
            @RequestPart(value = "file") @Valid @NotNull @NotBlank MultipartFile file) {
        return null;
    }

    //Tools detecting steganography
    //@see: https://github.com/jpducassou/StegExpose
    @RequestMapping(method = RequestMethod.POST, value = "/expose", consumes = MediaType.MULTIPART_FORM_DATA)
    @ApiOperation(value = "Response a string describing Stegona' picture is successfully exposed or not.", position = 3)
//	@ApiImplicitParams({@ApiImplicitParam(name="Authorization", value="Authorization DESCRIPTION")})
    public @ResponseBody
    StegonagraphyInfo exposing(
            @RequestParam(value = "tool", required = true, defaultValue =
                    "zsteg") StegonaDetectTools tool,
            @RequestPart(value = "file") @Valid @NotNull @NotBlank MultipartFile file) {
        return null;
    }

    // @see: https://spring.io/guides/gs/uploading-files/
    // General screening tools
    @RequestMapping(method = RequestMethod.POST, value = "/screen", consumes = MediaType.MULTIPART_FORM_DATA)
    @ApiOperation(value = "Response a string describing Stegona' picture is successfully screened or not.", position = 2)
//	@ApiImplicitParams({@ApiImplicitParam(name="Authorization", value="Authorization DESCRIPTION")})
    public @ResponseBody
    StegonagraphyInfo screening(
            @RequestParam(value = "tool", required = true, defaultValue =
                    "file") StegonaScreenTools tool,
            @RequestPart(value = "file") @Valid @NotNull @NotBlank MultipartFile file) {
        // @Validated MultipartFileWrapper file, BindingResult result, Principal
        // principal){
        long startTime = System.currentTimeMillis();
        StegonagraphyInfo stegonagraphyInfo = new StegonagraphyInfo();
        String fileName = null;
        if (!file.isEmpty()) {
            // ImageMagick convert options; @see:
            // http://paxcel.net/blog/java-thumbnail-generator-imagescalar-vs-imagemagic/
            Map<String, String> _imageMagickOutput = this.fileOperation(file);
            // Save to database.
            try {
                // Image resize operation.
                fileName = _imageMagickOutput.get(ImageSize.ori.toString());
                LOG.info("ImageMagick output success: " + fileName);
                String imageUrl = StegonagraphyInfoHelper.getRemoteImageUrl(fileName);
                stegonagraphyInfo.setUri(imageUrl);
//


            } catch (Exception ex) {
                LOG.error(ex.toString());
            }
        } else {
            LOG.error("You failed to upload " + file.getName() + " because the file was empty.");
        }
        return stegonagraphyInfo;
    }

    //
    @SuppressWarnings("unused")
    private String thumbnailImage(int width, int height, String source)
            throws IOException, InterruptedException, IM4JavaException {
        //
        String small4dbBase = FilenameUtils.getBaseName(source) + "_" + String.valueOf(width) + "x"
                + String.valueOf(height) + "." + FilenameUtils.getExtension(source);
        String small4db = FileUtil.getUploads() + small4dbBase;
        String small = getClassPath() + small4db;
        // @see:
        // http://paxcel.net/blog/java-thumbnail-generator-imagescalar-vs-imagemagic/
        ConvertCmd cmd = new ConvertCmd();
        // cmd.setSearchPath("");
        File thumbnailFile = new File(small);
        if (!thumbnailFile.exists()) {
            IMOperation op = new IMOperation();
            op.addImage(source);
            op.thumbnail(width);
            op.addImage(small);
            cmd.run(op);
            LOG.info("ImageMagick success result:" + small);
        }
        return small4dbBase;
    }

    // @Autowired
    // private FolderSetting folderSetting;

    private Map<String, String> fileOperation(MultipartFile file) {
        Map<String, String> _imageMagickOutput = new HashMap<String, String>();
        String dbFileName = null;
        String fullFileName = null;
        try {
            byte[] bytes = file.getBytes();
            String fileExt = FilenameUtils.getExtension(file.getOriginalFilename());
            String fileNameAppendix
                    // = "temp" + "." + fileExt;
                    = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss-SSS").format(new Date()) + "." + fileExt;

            dbFileName = FileUtil.getUploads() + fileNameAppendix;
            fullFileName = dbFileName;

            BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(fullFileName)));
            stream.write(bytes);
            stream.close();
            // System.out.println("Upload file success." + fullFileName);
            LOG.info("Upload file success." + fullFileName);
            // ImageMagick convert options; @see:
            // http://paxcel.net/blog/java-thumbnail-generator-imagescalar-vs-imagemagic/
            _imageMagickOutput.put(ImageSize.ori.toString(), fileNameAppendix);
            // _imageMagickOutput.put(ImageSize.sml.toString(),
            // thumbnailImage(150, 150, fullFileName));
            // _imageMagickOutput.put(ImageSize.ico.toString(),
            // thumbnailImage(32, 32, fullFileName));
            return _imageMagickOutput;
        } catch (Exception e) {
            // System.out.println("You failed to convert " + fullFileName + " =>
            // " + e.toString());
            LOG.error("You failed to convert " + fullFileName + " => " + e.toString());
        }
        return _imageMagickOutput;
    }

    private String getClassPath() {
        String classPath = this.getClass().getResource("/").getPath();
        return classPath;
    }

}
