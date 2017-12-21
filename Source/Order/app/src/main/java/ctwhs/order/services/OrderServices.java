package ctwhs.order.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;

import ctwhs.order.constant.ConstConfigs;
import ctwhs.order.dto.ServerInfoDto;

import static org.xmlpull.v1.XmlPullParser.END_TAG;

/**
 * Created by UNGTQ on 6/4/2017.
 */

public class OrderServices extends Service {

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private boolean isValidLogin(String username, String password) throws IOException, XmlPullParserException {
        InputStream inputStream = getAssets().open(ConstConfigs.CONFIGURATIONS_XML);

        XmlPullParserFactory pullParserFactory = XmlPullParserFactory.newInstance();
        XmlPullParser parser = pullParserFactory.newPullParser();
        parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);

        parser.setInput(inputStream, null);

        ServerInfoDto serverInfoDto = getServerInfo(parser);



        return true;
    }

    /**
     * get server info
     * @param xmlPullParser
     * @return ServerInfoDto
     * @throws XmlPullParserException
     * @throws IOException
     */
    private ServerInfoDto getServerInfo(XmlPullParser xmlPullParser) throws XmlPullParserException,IOException {
        ServerInfoDto serverInfoDto = new ServerInfoDto();
        int i;
        while ((i = xmlPullParser.next()) != XmlPullParser.END_DOCUMENT) {
            if (i == XmlPullParser.START_TAG) {
                String tagName = xmlPullParser.getName();
                Log.e("TQKy", tagName);
            } else if (i == XmlPullParser.TEXT) {
                String text = xmlPullParser.getText();
                Log.e("TQKy", text);

                if(ConstConfigs.CONFIGURATIONS_XML_SERVER_NAME_TAG.equals(xmlPullParser.getName())){
                    serverInfoDto.setServerName(xmlPullParser.getText());
                }

                if(ConstConfigs.CONFIGURATIONS_XML_SERVER_PORT_TAG.equals(xmlPullParser.getName())){
                    serverInfoDto.setPortNo(xmlPullParser.getText());
                }

            } else if (i == END_TAG) {
                String tagName = xmlPullParser.getName();
                Log.e("TQKy", tagName);
            }
        }

        return serverInfoDto;
    }
}
