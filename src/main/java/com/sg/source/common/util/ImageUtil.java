package com.sg.source.common.util;

import java.io.FileOutputStream;

public class ImageUtil {

    //TODO image reduce
    /*private String getImageViewToFileResizing(Bitmap bitmap){

        String mUri = "";


        if(bitmap != null) {
            FileOutputStream fout = null;
            try {
                int MAX_IMAGE_SIZE = 150 * 1024; // max final file size
                int compressQuality = 100; // quality decreasing by 5 every loop. (start from 99)
                int streamLength = MAX_IMAGE_SIZE;
                album_fileName = System.currentTimeMillis() + "_pic.jpg";

                File file = new File(dirFile,album_fileName);
                while(streamLength >= MAX_IMAGE_SIZE){
                    fout = new FileOutputStream(file);
                    bitmap.compress(Bitmap.CompressFormat.JPEG, compressQuality, fout);
                    streamLength = (int) file.length();
                    compressQuality -= 5;
                }

                fout.flush();
                mUri = file.getPath();


            } catch (Exception e) {
                e.printStackTrace();
            }
            finally{
                if(fout!=null){
                    try{
                        fout.close();
                    }catch(Exception e){
                    }
                }else{
                }
            }

        }

        return mUri;
    }*/

    //TODO image resize

    //TODO base64 encoding

    //TODO base64 decoding

}
