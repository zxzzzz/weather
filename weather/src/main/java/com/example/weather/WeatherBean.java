package com.example.weather;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by zx on 16-10-11.
 */
public class WeatherBean {

    /**
     * aqi : {"city":{"aqi":"201","co":"2","no2":"52","o3":"71","pm10":"109","pm25":"201","qlty":"中度污染","so2":"19"}}
     * basic : {"city":"北京","cnty":"中国","id":"CN101010100","lat":"39.904000","lon":"116.391000","update":{"loc":"2016-10-11 14:51","utc":"2016-10-11 06:51"}}
     * daily_forecast : [{"astro":{"sr":"06:20","ss":"17:41"},"cond":{"code_d":"100","code_n":"104","txt_d":"晴","txt_n":"阴"},"date":"2016-10-11","hum":"41","pcpn":"0.0","pop":"0","pres":"1024","tmp":{"max":"20","min":"10"},"vis":"10","wind":{"deg":"129","dir":"无持续风向","sc":"微风","spd":"4"}},{"astro":{"sr":"06:22","ss":"17:39"},"cond":{"code_d":"104","code_n":"101","txt_d":"阴","txt_n":"多云"},"date":"2016-10-12","hum":"74","pcpn":"0.0","pop":"5","pres":"1023","tmp":{"max":"18","min":"10"},"vis":"10","wind":{"deg":"200","dir":"无持续风向","sc":"微风","spd":"9"}},{"astro":{"sr":"06:23","ss":"17:38"},"cond":{"code_d":"100","code_n":"100","txt_d":"晴","txt_n":"晴"},"date":"2016-10-13","hum":"55","pcpn":"0.0","pop":"0","pres":"1021","tmp":{"max":"22","min":"11"},"vis":"10","wind":{"deg":"94","dir":"无持续风向","sc":"微风","spd":"4"}},{"astro":{"sr":"06:24","ss":"17:36"},"cond":{"code_d":"100","code_n":"104","txt_d":"晴","txt_n":"阴"},"date":"2016-10-14","hum":"71","pcpn":"0.0","pop":"0","pres":"1023","tmp":{"max":"22","min":"12"},"vis":"10","wind":{"deg":"152","dir":"无持续风向","sc":"微风","spd":"7"}},{"astro":{"sr":"06:25","ss":"17:35"},"cond":{"code_d":"305","code_n":"104","txt_d":"小雨","txt_n":"阴"},"date":"2016-10-15","hum":"66","pcpn":"0.0","pop":"0","pres":"1023","tmp":{"max":"17","min":"13"},"vis":"10","wind":{"deg":"199","dir":"无持续风向","sc":"微风","spd":"1"}},{"astro":{"sr":"06:26","ss":"17:33"},"cond":{"code_d":"104","code_n":"100","txt_d":"阴","txt_n":"晴"},"date":"2016-10-16","hum":"62","pcpn":"0.0","pop":"0","pres":"1022","tmp":{"max":"21","min":"11"},"vis":"10","wind":{"deg":"175","dir":"无持续风向","sc":"微风","spd":"8"}},{"astro":{"sr":"06:27","ss":"17:32"},"cond":{"code_d":"100","code_n":"100","txt_d":"晴","txt_n":"晴"},"date":"2016-10-17","hum":"41","pcpn":"0.0","pop":"0","pres":"1027","tmp":{"max":"20","min":"10"},"vis":"10","wind":{"deg":"133","dir":"无持续风向","sc":"微风","spd":"2"}}]
     * hourly_forecast : [{"date":"2016-10-11 16:00","hum":"36","pop":"0","pres":"1023","tmp":"19","wind":{"deg":"144","dir":"东南风","sc":"微风","spd":"9"}},{"date":"2016-10-11 19:00","hum":"45","pop":"0","pres":"1024","tmp":"17","wind":{"deg":"163","dir":"东南风","sc":"微风","spd":"8"}},{"date":"2016-10-11 22:00","hum":"59","pop":"0","pres":"1026","tmp":"14","wind":{"deg":"189","dir":"南风","sc":"微风","spd":"8"}}]
     * now : {"cond":{"code":"100","txt":"晴"},"fl":"19","hum":"65","pcpn":"0","pres":"1021","tmp":"17","vis":"4","wind":{"deg":"140","dir":"东南风","sc":"4-5","spd":"21"}}
     * status : ok
     * suggestion : {"comf":{"brf":"舒适","txt":"白天不太热也不太冷，风力不大，相信您在这样的天气条件下，应会感到比较清爽和舒适。"},"cw":{"brf":"较适宜","txt":"较适宜洗车，未来一天无雨，风力较小，擦洗一新的汽车至少能保持一天。"},"drsg":{"brf":"较舒适","txt":"建议着薄外套、开衫牛仔衫裤等服装。年老体弱者应适当添加衣物，宜着夹克衫、薄毛衣等。"},"flu":{"brf":"少发","txt":"各项气象条件适宜，无明显降温过程，发生感冒机率较低。"},"sport":{"brf":"较适宜","txt":"天气较好，但考虑气温较低，推荐您进行室内运动，若户外适当增减衣物并注意防晒。"},"trav":{"brf":"适宜","txt":"天气较好，温度适宜，是个好天气哦。这样的天气适宜旅游，您可以尽情地享受大自然的风光。"},"uv":{"brf":"中等","txt":"属中等强度紫外线辐射天气，外出时建议涂擦SPF高于15、PA+的防晒护肤品，戴帽子、太阳镜。"}}
     */

    @SerializedName("HeWeather data service 3.0")
    private List<HeWeatherDataServiceBean> HeWeatherDataService;

    public List<HeWeatherDataServiceBean> getHeWeatherDataService() {
        return HeWeatherDataService;
    }

    public void setHeWeatherDataService(List<HeWeatherDataServiceBean> HeWeatherDataService) {
        this.HeWeatherDataService = HeWeatherDataService;
    }

    public static class HeWeatherDataServiceBean {
        /**
         * city : {"aqi":"201","co":"2","no2":"52","o3":"71","pm10":"109","pm25":"201","qlty":"中度污染","so2":"19"}
         */

        @SerializedName("aqi")
        private AqiBean aqi;
        /**
         * city : 北京
         * cnty : 中国
         * id : CN101010100
         * lat : 39.904000
         * lon : 116.391000
         * update : {"loc":"2016-10-11 14:51","utc":"2016-10-11 06:51"}
         */

        @SerializedName("basic")
        private BasicBean basic;
        /**
         * cond : {"code":"100","txt":"晴"}
         * fl : 19
         * hum : 65
         * pcpn : 0
         * pres : 1021
         * tmp : 17
         * vis : 4
         * wind : {"deg":"140","dir":"东南风","sc":"4-5","spd":"21"}
         */

        @SerializedName("now")
        private NowBean now;
        @SerializedName("status")
        private String status;
        /**
         * comf : {"brf":"舒适","txt":"白天不太热也不太冷，风力不大，相信您在这样的天气条件下，应会感到比较清爽和舒适。"}
         * cw : {"brf":"较适宜","txt":"较适宜洗车，未来一天无雨，风力较小，擦洗一新的汽车至少能保持一天。"}
         * drsg : {"brf":"较舒适","txt":"建议着薄外套、开衫牛仔衫裤等服装。年老体弱者应适当添加衣物，宜着夹克衫、薄毛衣等。"}
         * flu : {"brf":"少发","txt":"各项气象条件适宜，无明显降温过程，发生感冒机率较低。"}
         * sport : {"brf":"较适宜","txt":"天气较好，但考虑气温较低，推荐您进行室内运动，若户外适当增减衣物并注意防晒。"}
         * trav : {"brf":"适宜","txt":"天气较好，温度适宜，是个好天气哦。这样的天气适宜旅游，您可以尽情地享受大自然的风光。"}
         * uv : {"brf":"中等","txt":"属中等强度紫外线辐射天气，外出时建议涂擦SPF高于15、PA+的防晒护肤品，戴帽子、太阳镜。"}
         */

        @SerializedName("suggestion")
        private SuggestionBean suggestion;
        /**
         * astro : {"sr":"06:20","ss":"17:41"}
         * cond : {"code_d":"100","code_n":"104","txt_d":"晴","txt_n":"阴"}
         * date : 2016-10-11
         * hum : 41
         * pcpn : 0.0
         * pop : 0
         * pres : 1024
         * tmp : {"max":"20","min":"10"}
         * vis : 10
         * wind : {"deg":"129","dir":"无持续风向","sc":"微风","spd":"4"}
         */

        @SerializedName("daily_forecast")
        private List<DailyForecastBean> dailyForecast;
        /**
         * date : 2016-10-11 16:00
         * hum : 36
         * pop : 0
         * pres : 1023
         * tmp : 19
         * wind : {"deg":"144","dir":"东南风","sc":"微风","spd":"9"}
         */

        @SerializedName("hourly_forecast")
        private List<HourlyForecastBean> hourlyForecast;

        public AqiBean getAqi() {
            return aqi;
        }

        public void setAqi(AqiBean aqi) {
            this.aqi = aqi;
        }

        public BasicBean getBasic() {
            return basic;
        }

        public void setBasic(BasicBean basic) {
            this.basic = basic;
        }

        public NowBean getNow() {
            return now;
        }

        public void setNow(NowBean now) {
            this.now = now;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public SuggestionBean getSuggestion() {
            return suggestion;
        }

        public void setSuggestion(SuggestionBean suggestion) {
            this.suggestion = suggestion;
        }

        public List<DailyForecastBean> getDailyForecast() {
            return dailyForecast;
        }

        public void setDailyForecast(List<DailyForecastBean> dailyForecast) {
            this.dailyForecast = dailyForecast;
        }

        public List<HourlyForecastBean> getHourlyForecast() {
            return hourlyForecast;
        }

        public void setHourlyForecast(List<HourlyForecastBean> hourlyForecast) {
            this.hourlyForecast = hourlyForecast;
        }

        public static class AqiBean {
            /**
             * aqi : 201
             * co : 2
             * no2 : 52
             * o3 : 71
             * pm10 : 109
             * pm25 : 201
             * qlty : 中度污染
             * so2 : 19
             */

            @SerializedName("city")
            private CityBean city;

            public CityBean getCity() {
                return city;
            }

            public void setCity(CityBean city) {
                this.city = city;
            }

            public static class CityBean {
                @SerializedName("aqi")
                private String aqi;
                @SerializedName("co")
                private String co;
                @SerializedName("no2")
                private String no2;
                @SerializedName("o3")
                private String o3;
                @SerializedName("pm10")
                private String pm10;
                @SerializedName("pm25")
                private String pm25;
                @SerializedName("qlty")
                private String qlty;
                @SerializedName("so2")
                private String so2;

                public String getAqi() {
                    return aqi;
                }

                public void setAqi(String aqi) {
                    this.aqi = aqi;
                }

                public String getCo() {
                    return co;
                }

                public void setCo(String co) {
                    this.co = co;
                }

                public String getNo2() {
                    return no2;
                }

                public void setNo2(String no2) {
                    this.no2 = no2;
                }

                public String getO3() {
                    return o3;
                }

                public void setO3(String o3) {
                    this.o3 = o3;
                }

                public String getPm10() {
                    return pm10;
                }

                public void setPm10(String pm10) {
                    this.pm10 = pm10;
                }

                public String getPm25() {
                    return pm25;
                }

                public void setPm25(String pm25) {
                    this.pm25 = pm25;
                }

                public String getQlty() {
                    return qlty;
                }

                public void setQlty(String qlty) {
                    this.qlty = qlty;
                }

                public String getSo2() {
                    return so2;
                }

                public void setSo2(String so2) {
                    this.so2 = so2;
                }
            }
        }

        public static class BasicBean {
            @SerializedName("city")
            private String city;
            @SerializedName("cnty")
            private String cnty;
            @SerializedName("id")
            private String id;
            @SerializedName("lat")
            private String lat;
            @SerializedName("lon")
            private String lon;
            /**
             * loc : 2016-10-11 14:51
             * utc : 2016-10-11 06:51
             */

            @SerializedName("update")
            private UpdateBean update;

            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
            }

            public String getCnty() {
                return cnty;
            }

            public void setCnty(String cnty) {
                this.cnty = cnty;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getLat() {
                return lat;
            }

            public void setLat(String lat) {
                this.lat = lat;
            }

            public String getLon() {
                return lon;
            }

            public void setLon(String lon) {
                this.lon = lon;
            }

            public UpdateBean getUpdate() {
                return update;
            }

            public void setUpdate(UpdateBean update) {
                this.update = update;
            }

            public static class UpdateBean {
                @SerializedName("loc")
                private String loc;
                @SerializedName("utc")
                private String utc;

                public String getLoc() {
                    return loc;
                }

                public void setLoc(String loc) {
                    this.loc = loc;
                }

                public String getUtc() {
                    return utc;
                }

                public void setUtc(String utc) {
                    this.utc = utc;
                }
            }
        }

        public static class NowBean {
            /**
             * code : 100
             * txt : 晴
             */

            @SerializedName("cond")
            private CondBean cond;
            @SerializedName("fl")
            private String fl;
            @SerializedName("hum")
            private String hum;
            @SerializedName("pcpn")
            private String pcpn;
            @SerializedName("pres")
            private String pres;
            @SerializedName("tmp")
            private String tmp;
            @SerializedName("vis")
            private String vis;
            /**
             * deg : 140
             * dir : 东南风
             * sc : 4-5
             * spd : 21
             */

            @SerializedName("wind")
            private WindBean wind;

            public CondBean getCond() {
                return cond;
            }

            public void setCond(CondBean cond) {
                this.cond = cond;
            }

            public String getFl() {
                return fl;
            }

            public void setFl(String fl) {
                this.fl = fl;
            }

            public String getHum() {
                return hum;
            }

            public void setHum(String hum) {
                this.hum = hum;
            }

            public String getPcpn() {
                return pcpn;
            }

            public void setPcpn(String pcpn) {
                this.pcpn = pcpn;
            }

            public String getPres() {
                return pres;
            }

            public void setPres(String pres) {
                this.pres = pres;
            }

            public String getTmp() {
                return tmp;
            }

            public void setTmp(String tmp) {
                this.tmp = tmp;
            }

            public String getVis() {
                return vis;
            }

            public void setVis(String vis) {
                this.vis = vis;
            }

            public WindBean getWind() {
                return wind;
            }

            public void setWind(WindBean wind) {
                this.wind = wind;
            }

            public static class CondBean {
                @SerializedName("code")
                private String code;
                @SerializedName("txt")
                private String txt;

                public String getCode() {
                    return code;
                }

                public void setCode(String code) {
                    this.code = code;
                }

                public String getTxt() {
                    return txt;
                }

                public void setTxt(String txt) {
                    this.txt = txt;
                }
            }

            public static class WindBean {
                @SerializedName("deg")
                private String deg;
                @SerializedName("dir")
                private String dir;
                @SerializedName("sc")
                private String sc;
                @SerializedName("spd")
                private String spd;

                public String getDeg() {
                    return deg;
                }

                public void setDeg(String deg) {
                    this.deg = deg;
                }

                public String getDir() {
                    return dir;
                }

                public void setDir(String dir) {
                    this.dir = dir;
                }

                public String getSc() {
                    return sc;
                }

                public void setSc(String sc) {
                    this.sc = sc;
                }

                public String getSpd() {
                    return spd;
                }

                public void setSpd(String spd) {
                    this.spd = spd;
                }
            }
        }

        public static class SuggestionBean {
            /**
             * brf : 舒适
             * txt : 白天不太热也不太冷，风力不大，相信您在这样的天气条件下，应会感到比较清爽和舒适。
             */

            @SerializedName("comf")
            private ComfBean comf;
            /**
             * brf : 较适宜
             * txt : 较适宜洗车，未来一天无雨，风力较小，擦洗一新的汽车至少能保持一天。
             */

            @SerializedName("cw")
            private CwBean cw;
            /**
             * brf : 较舒适
             * txt : 建议着薄外套、开衫牛仔衫裤等服装。年老体弱者应适当添加衣物，宜着夹克衫、薄毛衣等。
             */

            @SerializedName("drsg")
            private DrsgBean drsg;
            /**
             * brf : 少发
             * txt : 各项气象条件适宜，无明显降温过程，发生感冒机率较低。
             */

            @SerializedName("flu")
            private FluBean flu;
            /**
             * brf : 较适宜
             * txt : 天气较好，但考虑气温较低，推荐您进行室内运动，若户外适当增减衣物并注意防晒。
             */

            @SerializedName("sport")
            private SportBean sport;
            /**
             * brf : 适宜
             * txt : 天气较好，温度适宜，是个好天气哦。这样的天气适宜旅游，您可以尽情地享受大自然的风光。
             */

            @SerializedName("trav")
            private TravBean trav;
            /**
             * brf : 中等
             * txt : 属中等强度紫外线辐射天气，外出时建议涂擦SPF高于15、PA+的防晒护肤品，戴帽子、太阳镜。
             */

            @SerializedName("uv")
            private UvBean uv;

            public ComfBean getComf() {
                return comf;
            }

            public void setComf(ComfBean comf) {
                this.comf = comf;
            }

            public CwBean getCw() {
                return cw;
            }

            public void setCw(CwBean cw) {
                this.cw = cw;
            }

            public DrsgBean getDrsg() {
                return drsg;
            }

            public void setDrsg(DrsgBean drsg) {
                this.drsg = drsg;
            }

            public FluBean getFlu() {
                return flu;
            }

            public void setFlu(FluBean flu) {
                this.flu = flu;
            }

            public SportBean getSport() {
                return sport;
            }

            public void setSport(SportBean sport) {
                this.sport = sport;
            }

            public TravBean getTrav() {
                return trav;
            }

            public void setTrav(TravBean trav) {
                this.trav = trav;
            }

            public UvBean getUv() {
                return uv;
            }

            public void setUv(UvBean uv) {
                this.uv = uv;
            }

            public static class ComfBean {
                @SerializedName("brf")
                private String brf;
                @SerializedName("txt")
                private String txt;

                public String getBrf() {
                    return brf;
                }

                public void setBrf(String brf) {
                    this.brf = brf;
                }

                public String getTxt() {
                    return txt;
                }

                public void setTxt(String txt) {
                    this.txt = txt;
                }
            }

            public static class CwBean {
                @SerializedName("brf")
                private String brf;
                @SerializedName("txt")
                private String txt;

                public String getBrf() {
                    return brf;
                }

                public void setBrf(String brf) {
                    this.brf = brf;
                }

                public String getTxt() {
                    return txt;
                }

                public void setTxt(String txt) {
                    this.txt = txt;
                }
            }

            public static class DrsgBean {
                @SerializedName("brf")
                private String brf;
                @SerializedName("txt")
                private String txt;

                public String getBrf() {
                    return brf;
                }

                public void setBrf(String brf) {
                    this.brf = brf;
                }

                public String getTxt() {
                    return txt;
                }

                public void setTxt(String txt) {
                    this.txt = txt;
                }
            }

            public static class FluBean {
                @SerializedName("brf")
                private String brf;
                @SerializedName("txt")
                private String txt;

                public String getBrf() {
                    return brf;
                }

                public void setBrf(String brf) {
                    this.brf = brf;
                }

                public String getTxt() {
                    return txt;
                }

                public void setTxt(String txt) {
                    this.txt = txt;
                }
            }

            public static class SportBean {
                @SerializedName("brf")
                private String brf;
                @SerializedName("txt")
                private String txt;

                public String getBrf() {
                    return brf;
                }

                public void setBrf(String brf) {
                    this.brf = brf;
                }

                public String getTxt() {
                    return txt;
                }

                public void setTxt(String txt) {
                    this.txt = txt;
                }
            }

            public static class TravBean {
                @SerializedName("brf")
                private String brf;
                @SerializedName("txt")
                private String txt;

                public String getBrf() {
                    return brf;
                }

                public void setBrf(String brf) {
                    this.brf = brf;
                }

                public String getTxt() {
                    return txt;
                }

                public void setTxt(String txt) {
                    this.txt = txt;
                }
            }

            public static class UvBean {
                @SerializedName("brf")
                private String brf;
                @SerializedName("txt")
                private String txt;

                public String getBrf() {
                    return brf;
                }

                public void setBrf(String brf) {
                    this.brf = brf;
                }

                public String getTxt() {
                    return txt;
                }

                public void setTxt(String txt) {
                    this.txt = txt;
                }
            }
        }

        public static class DailyForecastBean {
            /**
             * sr : 06:20
             * ss : 17:41
             */

            @SerializedName("astro")
            private AstroBean astro;
            /**
             * code_d : 100
             * code_n : 104
             * txt_d : 晴
             * txt_n : 阴
             */

            @SerializedName("cond")
            private CondBean cond;
            @SerializedName("date")
            private String date;
            @SerializedName("hum")
            private String hum;
            @SerializedName("pcpn")
            private String pcpn;
            @SerializedName("pop")
            private String pop;
            @SerializedName("pres")
            private String pres;
            /**
             * max : 20
             * min : 10
             */

            @SerializedName("tmp")
            private TmpBean tmp;
            @SerializedName("vis")
            private String vis;
            /**
             * deg : 129
             * dir : 无持续风向
             * sc : 微风
             * spd : 4
             */

            @SerializedName("wind")
            private WindBean wind;

            public AstroBean getAstro() {
                return astro;
            }

            public void setAstro(AstroBean astro) {
                this.astro = astro;
            }

            public CondBean getCond() {
                return cond;
            }

            public void setCond(CondBean cond) {
                this.cond = cond;
            }

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public String getHum() {
                return hum;
            }

            public void setHum(String hum) {
                this.hum = hum;
            }

            public String getPcpn() {
                return pcpn;
            }

            public void setPcpn(String pcpn) {
                this.pcpn = pcpn;
            }

            public String getPop() {
                return pop;
            }

            public void setPop(String pop) {
                this.pop = pop;
            }

            public String getPres() {
                return pres;
            }

            public void setPres(String pres) {
                this.pres = pres;
            }

            public TmpBean getTmp() {
                return tmp;
            }

            public void setTmp(TmpBean tmp) {
                this.tmp = tmp;
            }

            public String getVis() {
                return vis;
            }

            public void setVis(String vis) {
                this.vis = vis;
            }

            public WindBean getWind() {
                return wind;
            }

            public void setWind(WindBean wind) {
                this.wind = wind;
            }

            public static class AstroBean {
                @SerializedName("sr")
                private String sr;
                @SerializedName("ss")
                private String ss;

                public String getSr() {
                    return sr;
                }

                public void setSr(String sr) {
                    this.sr = sr;
                }

                public String getSs() {
                    return ss;
                }

                public void setSs(String ss) {
                    this.ss = ss;
                }
            }

            public static class CondBean {
                @SerializedName("code_d")
                private String codeD;
                @SerializedName("code_n")
                private String codeN;
                @SerializedName("txt_d")
                private String txtD;
                @SerializedName("txt_n")
                private String txtN;

                public String getCodeD() {
                    return codeD;
                }

                public void setCodeD(String codeD) {
                    this.codeD = codeD;
                }

                public String getCodeN() {
                    return codeN;
                }

                public void setCodeN(String codeN) {
                    this.codeN = codeN;
                }

                public String getTxtD() {
                    return txtD;
                }

                public void setTxtD(String txtD) {
                    this.txtD = txtD;
                }

                public String getTxtN() {
                    return txtN;
                }

                public void setTxtN(String txtN) {
                    this.txtN = txtN;
                }
            }

            public static class TmpBean {
                @SerializedName("max")
                private String max;
                @SerializedName("min")
                private String min;

                public String getMax() {
                    return max;
                }

                public void setMax(String max) {
                    this.max = max;
                }

                public String getMin() {
                    return min;
                }

                public void setMin(String min) {
                    this.min = min;
                }
            }

            public static class WindBean {
                @SerializedName("deg")
                private String deg;
                @SerializedName("dir")
                private String dir;
                @SerializedName("sc")
                private String sc;
                @SerializedName("spd")
                private String spd;

                public String getDeg() {
                    return deg;
                }

                public void setDeg(String deg) {
                    this.deg = deg;
                }

                public String getDir() {
                    return dir;
                }

                public void setDir(String dir) {
                    this.dir = dir;
                }

                public String getSc() {
                    return sc;
                }

                public void setSc(String sc) {
                    this.sc = sc;
                }

                public String getSpd() {
                    return spd;
                }

                public void setSpd(String spd) {
                    this.spd = spd;
                }
            }
        }

        public static class HourlyForecastBean {
            @SerializedName("date")
            private String date;
            @SerializedName("hum")
            private String hum;
            @SerializedName("pop")
            private String pop;
            @SerializedName("pres")
            private String pres;
            @SerializedName("tmp")
            private String tmp;
            /**
             * deg : 144
             * dir : 东南风
             * sc : 微风
             * spd : 9
             */

            @SerializedName("wind")
            private WindBean wind;

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public String getHum() {
                return hum;
            }

            public void setHum(String hum) {
                this.hum = hum;
            }

            public String getPop() {
                return pop;
            }

            public void setPop(String pop) {
                this.pop = pop;
            }

            public String getPres() {
                return pres;
            }

            public void setPres(String pres) {
                this.pres = pres;
            }

            public String getTmp() {
                return tmp;
            }

            public void setTmp(String tmp) {
                this.tmp = tmp;
            }

            public WindBean getWind() {
                return wind;
            }

            public void setWind(WindBean wind) {
                this.wind = wind;
            }

            public static class WindBean {
                @SerializedName("deg")
                private String deg;
                @SerializedName("dir")
                private String dir;
                @SerializedName("sc")
                private String sc;
                @SerializedName("spd")
                private String spd;

                public String getDeg() {
                    return deg;
                }

                public void setDeg(String deg) {
                    this.deg = deg;
                }

                public String getDir() {
                    return dir;
                }

                public void setDir(String dir) {
                    this.dir = dir;
                }

                public String getSc() {
                    return sc;
                }

                public void setSc(String sc) {
                    this.sc = sc;
                }

                public String getSpd() {
                    return spd;
                }

                public void setSpd(String spd) {
                    this.spd = spd;
                }
            }
        }
    }
}
