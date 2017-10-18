package com.rentalcar.controllers.command.admin;

import com.rentalcar.controllers.command.Command;
import com.rentalcar.constants.CommandConstants;
import com.rentalcar.constants.MessageWrapper;
import com.rentalcar.controllers.security.Crypto;
import com.rentalcar.controllers.services.AdminService;
import com.rentalcar.models.builders.CarBuilder;
import com.rentalcar.models.builders.EntityBuilderException;
import com.rentalcar.models.car.*;
import com.rentalcar.models.user.Account;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.net.MalformedURLException;
import java.util.Date;
import java.util.List;

import static com.rentalcar.constants.RequestConstants.CarAttributes.*;

public class AddCar implements Command {

    private static final int MAX_MEM_SIZE = 100 * 1024;
    private static final int MAX_FILE_SIZE = 500 * 1024;
    private static final int FIELD_NUMBER = 5;

    private static final String IMG_PATH = File.separator + "img" + File.separator;
    private static final String IMG_EXT = ".jpg";

    private static final Logger log = Logger.getLogger(AddCar.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        // if user is admin and logged in
        Account user = getUser(request);
        if(user == null || !user.isAdmin())
            return CommandConstants.Pages.ERROR;

        Car car = getCar(request);
        if(car == null || !AdminService.getInstance().addCar(car))
            setMessage(request, MessageWrapper.Error.CREATE_CAR);

        return getPreviousView(request);
    }

      private Car getCar(HttpServletRequest request){
        Car car = null;
        List fields = getFields(request);
        if(fields != null){
            CarBuilder builder = new CarBuilder();
            fillBuilder(builder, fields);
            if(!builder.hasError())
                createImage( request, builder, fields);
            try {
                car = builder.getCar();
            } catch (EntityBuilderException e) {
                log.error(e.getMessage());
                setMessage(request, builder.getErrorMessage());
            }
        }
        return car;
    }

    private void createImage(HttpServletRequest request, CarBuilder builder, List fields){
        ServletContext servletContext = request.getServletContext();
        try {
            String imgName = Crypto.md5(builder.getModel() + new Date());
            String filePath = String.format("%s%s%s%s",
                    servletContext.getResource(IMG_PATH).getPath(), File.separator,
                    imgName, IMG_EXT);
            if(uploadFile(fields, filePath))
                builder.setImgName(imgName);
        } catch (MalformedURLException e) {
            log.error(e.getMessage());
        }
    }

    private boolean uploadFile( List fields, String filePath) {
        for (Object field : fields) {
            FileItem item = (FileItem) field;
            if (!item.isFormField()){
                File file = new File(filePath);
                try {
                    item.write(file);
                    return true;
                } catch (Exception e) {
                    log.error(e.getMessage());
                }
            }
        }
        return false;
    }

    private void fillBuilder(CarBuilder builder, List fields){
        if(fields.size() != FIELD_NUMBER)
            return;
        for (Object field : fields) {
            FileItem item = (FileItem) field;
            if (item.isFormField())
                setField(builder, item);
        }
    }

    private void setField(CarBuilder builder, FileItem item) {
        switch (item.getFieldName()){
            case CAR_MODEL:
                builder.setModel(item.getString());
                break;
            case CAR_TRANSMISSION:
                builder.setTransmission(TransmissionType.valueOf(item.getString()));
                break;
            case CAR_VEHICLE:
                builder.setVehicle(VehicleType.valueOf(item.getString()));
                break;
            case CAR_DAILY_PRICE:
                builder.setDailyPrice(item.getString());
                break;
            default: break;
        }
    }

    private List getFields(HttpServletRequest request){
        if( ServletFileUpload.isMultipartContent(request)){
            DiskFileItemFactory factory = new DiskFileItemFactory();
            factory.setSizeThreshold(MAX_MEM_SIZE);
            ServletFileUpload upload = new ServletFileUpload(factory);
            upload.setFileSizeMax(MAX_FILE_SIZE);
            try {
                return upload.parseRequest(request);
            } catch (FileUploadException e) {
                log.error(e.getMessage());
                setMessage(request, MessageWrapper.Error.UPLOAD_FILE);
            }
        }
        return null;
    }
}
