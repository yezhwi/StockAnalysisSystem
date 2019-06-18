package com.example.home_fragment;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.stockanalysissystem.R;
import com.example.home_fragment.ThemeDB.healthChinaStock;
import com.example.home_fragment.ThemeDB.greenCarStock;
import com.example.home_fragment.ThemeDB.xaNewAreaStock;
import com.example.home_fragment.ThemeDB.saveEnergyStock;
import com.example.home_fragment.ThemeDB.aiStock;
import com.example.home_fragment.ThemeDB.fgStock;
import com.example.home_fragment.ThemeDB.oneRoadStock;
import com.example.home_fragment.ThemeDB.vrStock;
import com.example.home_fragment.ThemeDB.yqStock;
import com.example.market_fragment.db.StockInfoDB;
import org.litepal.LitePal;

import java.util.List;

public class AddStockToTheme extends AppCompatActivity implements View.OnClickListener {
    private Spinner spinner,low_spinner;
    private EditText add_gid;
    private Button confirm_but;
    private String spinner_text=null,low_spinner_text=null,gid;
    private String[] ctype;
    private ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_stock_to_theme);
        spinner=(Spinner)findViewById(R.id.my_spinner);
        low_spinner=(Spinner)findViewById(R.id.my_low_spinner);
        add_gid=(EditText)findViewById(R.id.add_gid);
        confirm_but=(Button)findViewById(R.id.confirm_but);
        confirm_but.setOnClickListener(this);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                //拿到被选择项的值
                spinner_text = (String) spinner.getSelectedItem();
                if(spinner_text.equalsIgnoreCase("健康中国")){
                    ctype=new String[]{"医疗投资","新型医疗","医疗器械","医疗机构","食品保健","信息化医疗"};
                }else
                if(spinner_text.equalsIgnoreCase("新能源汽车")){
                    ctype=new String[]{"充电桩","电机电控","车辆制造","动力电池"};
                }else if(spinner_text.equalsIgnoreCase("雄安新区")){
                    ctype=new String[]{"智慧雄安","雄安能源","雄安地产","雄安基建","雄安城市设计","雄安环保"
                            ,"河北本地股","园林PPP","雄安交运","雄安金融","雄安特色小镇"};
                }else if(spinner_text.equalsIgnoreCase("节能环保")){
                    ctype=new String[]{"园林绿化","环境修复","污水处理","固废处理","大气治理","环境监测"
                            ,"核辐射防治"};
                }else if(spinner_text.equalsIgnoreCase("虚拟现实")){
                    ctype=new String[]{"软件或系统平台","控股或战略合作","硬件制造","视听触展示等后台配套",
                            "设备自营"};
                }else if(spinner_text.equalsIgnoreCase("一带一路")){
                    ctype=new String[]{"管道","建筑装饰","农业机械","汽车制造","核电","原油管道"
                            ,"工程建设","水泥","油气设备","工程机械","天然气管道","航空制造",
                            "光伏","铁路运输","玻璃","航空运输","水上运输","建材","输电","公路运输",
                            "风电","船舶制造"};
                }else if(spinner_text.equalsIgnoreCase("央企混改")){
                    ctype=new String[]{"国机系","国家电投系","南方电网","中核系","华电系","大唐系"
                            ,"华能系","兵装系","中石油系","国网系","国电系","中粮系", "中远海运系",
                            "中国建材系","中石化系","中国铁路系","国投系","中科院系","清华系","中化系",
                            "恒天系","华润系","五矿系","招商局系","中航工业系","中交系","联通系",
                            "中国电科系","航天科技系","航发系","中国节能系","兵工系","北大系","中国化工系",
                            "中信系","东航系","宝武钢铁系","一汽系","新兴际华系","国药系","中车系",
                            "航天科工系","中国电子系","中船重工系","哈电系","中国诚通系","中船系"
                    };
                }else if(spinner_text.equalsIgnoreCase("人工智能")){
                    ctype=new String[]{"基带芯片","大数据","云计算","射频器件","光通讯","芯片"
                            ,"指纹识别","语音识别","人脸识别","虹膜识别","终端","智能安防", "运营商",
                            "智慧城市","车联网","智能家居","智能医疗","智慧教育","智能交通","无人驾驶",
                            "无人机"
                    };
                }else if(spinner_text.equalsIgnoreCase("5G")){
                    ctype=new String[]{"基带芯片","大数据","云计算","射频器件","光通信","芯片"
                            ,"基站天线","通讯传输设备","通信网络建设","终端","智能安防", "运营商",
                            "智慧城市","车联网","智能家居","智能医疗","智慧教育","智能交通","无人驾驶",
                            "无人机"
                    };
                }

                adapter=new ArrayAdapter<String>(getBaseContext(),android.R.layout.simple_spinner_item,ctype);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
                low_spinner.setAdapter(adapter);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub

            }
        });

        /*
         * 健康中国,新能源汽车,雄安新区,节能环保,虚拟现实,一带一路,央企混改,人工智能,5G*/
        //spinner第二层
        //默认是健康中国的子目录
        ctype=new String[]{"医疗投资","新型医疗","医疗器械","医疗机构","食品保健","信息化医疗"};
        /*
        if(spinner_text.equalsIgnoreCase("健康中国")){
            ctype=new String[]{"医疗投资","新型医疗","医疗器械","医疗机构","食品保健","信息化医疗"};
        }else
        if(spinner_text.equalsIgnoreCase("新能源汽车")){
            ctype=new String[]{"充电桩","电机电控","车辆制造","动力电池"};
        }else if(spinner_text.equalsIgnoreCase("雄安新区")){
            ctype=new String[]{"智慧雄安","雄安能源","雄安地产","雄安基建","雄安城市设计","雄安环保"
                    ,"河北本地股","园林PPP","雄安交运","雄安金融","雄安特色小镇"};
        }else if(spinner_text.equalsIgnoreCase("节能环保")){
            ctype=new String[]{"园林绿化","环境修复","污水处理","固废处理","大气治理","环境监测"
                    ,"核辐射防治"};
        }else if(spinner_text.equalsIgnoreCase("虚拟现实")){
            ctype=new String[]{"软件或系统平台","控股或战略合作","硬件制造","视听触展示等后台配套",
                    "设备自营"};
        }else if(spinner_text.equalsIgnoreCase("一带一路")){
            ctype=new String[]{"管道","建筑装饰","农业机械","汽车制造","核电","原油管道"
                    ,"工程建设","水泥","油气设备","工程机械","天然气管道","航空制造",
                    "光伏","铁路运输","玻璃","航空运输","水上运输","建材","输电","公路运输",
                    "风电","船舶制造"};
        }else if(spinner_text.equalsIgnoreCase("央企混改")){
            ctype=new String[]{"国电系","国家电投系","南方电网","中核系","华电系","大唐系"
                    ,"华能系","兵装系","中石油系","国网系","国电系","中粮系", "中远海运系",
                    "中国建材系","中石化系","中国铁路系","国投系","中科院系","清华系","中化系",
                    "恒天系","华润系","五矿系","招商局系","中航工业系","中交系","联通系",
                    "中国电科系","航天科技系","航发系","中国节能系","兵工系","北大系","中国化工系",
                    "中信系","东航系","宝武钢铁系","一汽系","新兴际华系","国药系","中车系",
                    "航天科工系","中国电子系","中船重工系","哈电系","中国诚通系","中船系"
            };
        }else if(spinner_text.equalsIgnoreCase("人工智能")){
            ctype=new String[]{"基带芯片","大数据","云计算","射频器件","光通讯","芯片"
                    ,"指纹识别","语音识别","人脸识别","虹膜识别","终端","智能安防", "运营商",
                    "智慧城市","车联网","智能家居","智能医疗","智慧教育","智能交通","无人驾驶",
                    "无人机"
            };
        }else if(spinner_text.equalsIgnoreCase("5G")){
            ctype=new String[]{"基带芯片","大数据","云计算","射频器件","光通信","芯片"
                    ,"基站天线","通讯传输设备","通信网络建设","终端","智能安防", "运营商",
                    "智慧城市","车联网","智能家居","智能医疗","智慧教育","智能交通","无人驾驶",
                    "无人机"
            };
        }

        */
        adapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,ctype);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        low_spinner.setAdapter(adapter);

        if(spinner_text!=null){
            Log.i("spinner_text","not null");
        }else{
            Log.i("spinner_text","null");
        }



        low_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                //拿到被选择项的值
                low_spinner_text = (String)low_spinner.getSelectedItem();
                Log.i("low_spinner_text",low_spinner_text);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub

            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.confirm_but:
                add_Stock();
                add_gid.setText("");
                //Toast.makeText(getBaseContext(),spinner_text+""+low_spinner_text+"添加成功",Toast.LENGTH_SHORT).show();
                //LitePal.deleteAll(healthChinaStock.class);
                List<healthChinaStock> stock2= LitePal.findAll(healthChinaStock.class);
                Log.i("stock2.size：",""+stock2.size());
                break;
            default:
                break;
        }
    }

    private void add_Stock(){

        /*
        * 健康中国,新能源汽车,雄安新区,节能环保,虚拟现实,一带一路,央企混改,人工智能,5G*/
        if(spinner_text.equalsIgnoreCase("健康中国")){
            gid=add_gid.getText().toString();
            if(low_spinner_text.equalsIgnoreCase("医疗投资")){
                insertToChinaHealthDB(gid,"1");
               // Toast.makeText(getBaseContext(),spinner_text+""+low_spinner_text+"添加成功",Toast.LENGTH_SHORT).show();
            }else if(low_spinner_text.equalsIgnoreCase("新型医疗")){
                insertToChinaHealthDB(gid,"2");
                //Toast.makeText(getBaseContext(),spinner_text+""+low_spinner_text+"添加成功",Toast.LENGTH_SHORT).show();
            }else if(low_spinner_text.equalsIgnoreCase("医疗器械")){
                insertToChinaHealthDB(gid,"3");
                //Toast.makeText(getBaseContext(),spinner_text+""+low_spinner_text+"添加成功",Toast.LENGTH_SHORT).show();
            }else if(low_spinner_text.equalsIgnoreCase("医疗机构")){
                insertToChinaHealthDB(gid,"4");
                //Toast.makeText(getBaseContext(),spinner_text+""+low_spinner_text+"添加成功",Toast.LENGTH_SHORT).show();
            }else if(low_spinner_text.equalsIgnoreCase("食品保健")){
                insertToChinaHealthDB(gid,"5");
                //Toast.makeText(getBaseContext(),spinner_text+""+low_spinner_text+"添加成功",Toast.LENGTH_SHORT).show();
            }
            else if(low_spinner_text.equalsIgnoreCase("信息化医疗")){
                insertToChinaHealthDB(gid,"6");
               // Toast.makeText(getBaseContext(),spinner_text+""+low_spinner_text+"添加成功",Toast.LENGTH_SHORT).show();
            }
        }else if(spinner_text.equalsIgnoreCase("新能源汽车")){
            //"充电桩","电机电控","车辆制造","动力电池"
            gid=add_gid.getText().toString();
            if(low_spinner_text.equalsIgnoreCase("充电桩")){
                insertToGreenCarDB(gid,"1");
            }else if(low_spinner_text.equalsIgnoreCase("电机电控")){
                insertToGreenCarDB(gid,"2");
            }else if(low_spinner_text.equalsIgnoreCase("车辆制造")){
                insertToGreenCarDB(gid,"3");
            }else if(low_spinner_text.equalsIgnoreCase("动力电池")){
                insertToGreenCarDB(gid,"4");
            }
        }else if(spinner_text.equalsIgnoreCase("雄安新区")){
            //"智慧雄安","雄安能源","雄安地产","雄安基建","雄安城市设计","雄安环保",
            //"河北本地股","园林PPP","雄安交运","雄安金融","雄安特色小镇"
            gid=add_gid.getText().toString();
            if(low_spinner_text.equalsIgnoreCase("智慧雄安")){
                insertToXANewAreaDB(gid,"1");
            }else if(low_spinner_text.equalsIgnoreCase("雄安能源")){
                insertToXANewAreaDB(gid,"2");
            }else if(low_spinner_text.equalsIgnoreCase("雄安地产")){
                insertToXANewAreaDB(gid,"3");
            }else if(low_spinner_text.equalsIgnoreCase("雄安基建")){
                insertToXANewAreaDB(gid,"4");
            }else if(low_spinner_text.equalsIgnoreCase("雄安城市设计")){
                insertToXANewAreaDB(gid,"5");
            }else if(low_spinner_text.equalsIgnoreCase("雄安环保")){
                insertToXANewAreaDB(gid,"6");
            }else if(low_spinner_text.equalsIgnoreCase("河北本地股")){
                insertToXANewAreaDB(gid,"7");
            }else if(low_spinner_text.equalsIgnoreCase("园林PPP")){
                insertToXANewAreaDB(gid,"8");
            }else if(low_spinner_text.equalsIgnoreCase("雄安交运")){
                insertToXANewAreaDB(gid,"9");
            }else if(low_spinner_text.equalsIgnoreCase("雄安金融")){
                insertToXANewAreaDB(gid,"10");
            }else if(low_spinner_text.equalsIgnoreCase("雄安特色小镇")){
                insertToXANewAreaDB(gid,"11");
            }
        }else if(spinner_text.equalsIgnoreCase("节能环保")){
            //"园林绿化","环境修复","污水处理","固废处理","大气治理","环境监测","核辐射防治"
            gid=add_gid.getText().toString();
            if(low_spinner_text.equalsIgnoreCase("园林绿化")){
                insertToSaveEnergyDB(gid,"1");
            }else if(low_spinner_text.equalsIgnoreCase("环境修复")){
                insertToSaveEnergyDB(gid,"2");
            }else if(low_spinner_text.equalsIgnoreCase("污水处理")){
                insertToSaveEnergyDB(gid,"3");
            }else if(low_spinner_text.equalsIgnoreCase("固废处理")){
                insertToSaveEnergyDB(gid,"4");
            }else if(low_spinner_text.equalsIgnoreCase("大气治理")){
                insertToSaveEnergyDB(gid,"5");
            }else if(low_spinner_text.equalsIgnoreCase("环境监测")){
                insertToSaveEnergyDB(gid,"6");
            }else if(low_spinner_text.equalsIgnoreCase("核辐射防治")){
                insertToSaveEnergyDB(gid,"7");
            }
        }else if(spinner_text.equalsIgnoreCase("虚拟现实")){
            //"软件或系统平台","控股或战略合作","硬件制造","视听触展示等后台配套","设备自营"
            gid=add_gid.getText().toString();
            if(low_spinner_text.equalsIgnoreCase("软件或系统平台")){
                insertToVRDB(gid,"1");
            }else if(low_spinner_text.equalsIgnoreCase("控股或战略合作")){
                insertToVRDB(gid,"2");
            }else if(low_spinner_text.equalsIgnoreCase("硬件制造")){
                insertToVRDB(gid,"3");
            }else if(low_spinner_text.equalsIgnoreCase("视听触展示等后台配套")){
                insertToVRDB(gid,"4");
            }else if(low_spinner_text.equalsIgnoreCase("设备自营")){
                insertToVRDB(gid,"5");
            }
        }else if(spinner_text.equalsIgnoreCase("一带一路")){
            //"管道","建筑装饰","农业机械","汽车制造","核电","原油管道"
            // ,"工程建设","水泥","油气设备","工程机械","天然气管道","航空制造",
            // "光伏","铁路运输","玻璃","航空运输","水上运输","建材","输电","公路运输",
            // "风电","船舶制造"
            gid=add_gid.getText().toString();
            if(low_spinner_text.equalsIgnoreCase("管道")){
                insertToOneRoadDB(gid,"1");
            }else if(low_spinner_text.equalsIgnoreCase("建筑装饰")){
                insertToOneRoadDB(gid,"2");
            }else if(low_spinner_text.equalsIgnoreCase("农业机械")){
                insertToOneRoadDB(gid,"3");
            }else if(low_spinner_text.equalsIgnoreCase("汽车制造")){
                insertToOneRoadDB(gid,"4");
            }else if(low_spinner_text.equalsIgnoreCase("核电")){
                insertToOneRoadDB(gid,"5");
            }else if(low_spinner_text.equalsIgnoreCase("原油管道")){
                insertToOneRoadDB(gid,"6");
            }else if(low_spinner_text.equalsIgnoreCase("工程建设")){
                insertToOneRoadDB(gid,"7");
            }else if(low_spinner_text.equalsIgnoreCase("水泥")){
                insertToOneRoadDB(gid,"8");
            }else if(low_spinner_text.equalsIgnoreCase("油气设备")){
                insertToOneRoadDB(gid,"9");
            }else if(low_spinner_text.equalsIgnoreCase("工程机械")){
                insertToOneRoadDB(gid,"10");
            }else if(low_spinner_text.equalsIgnoreCase("天然气管道")){
                insertToOneRoadDB(gid,"11");
            }else if(low_spinner_text.equalsIgnoreCase("航空制造")){
                insertToOneRoadDB(gid,"12");
            }else if(low_spinner_text.equalsIgnoreCase("光伏")){
                insertToOneRoadDB(gid,"13");
            }else if(low_spinner_text.equalsIgnoreCase("铁路运输")){
                insertToOneRoadDB(gid,"14");
            }else if(low_spinner_text.equalsIgnoreCase("玻璃")){
                insertToOneRoadDB(gid,"15");
            }else if(low_spinner_text.equalsIgnoreCase("航空运输")){
                insertToOneRoadDB(gid,"16");
            }else if(low_spinner_text.equalsIgnoreCase("水上运输")){
                insertToOneRoadDB(gid,"17");
            }else if(low_spinner_text.equalsIgnoreCase("建材")){
                insertToOneRoadDB(gid,"18");
            }else if(low_spinner_text.equalsIgnoreCase("输电")){
                insertToOneRoadDB(gid,"19");
            }else if(low_spinner_text.equalsIgnoreCase("公路运输")){
                insertToOneRoadDB(gid,"20");
            }else if(low_spinner_text.equalsIgnoreCase("风电")){
                insertToOneRoadDB(gid,"21");
            }else if(low_spinner_text.equalsIgnoreCase("船舶制造")){
                insertToOneRoadDB(gid,"22");
            }
        }else if(spinner_text.equalsIgnoreCase("央企混改")){
            //"国机系","国家电投系","南方电网","中核系","华电系","大唐系"
            //,"华能系","兵装系","中石油系","国网系","国电系","中粮系", "中远海运系",
            //"中国建材系","中石化系","中国铁路系","国投系","中科院系","清华系","中化系",
            //恒天系","华润系","五矿系","招商局系","中航工业系","中交系","联通系",
            //"中国电科系","航天科技系","航发系","中国节能系","兵工系","北大系","中国化工系",
            //"中信系","东航系","宝武钢铁系","一汽系","新兴际华系","国药系","中车系",
            //"航天科工系","中国电子系","中船重工系","哈电系","中国诚通系","中船系"
            gid=add_gid.getText().toString();
            if(low_spinner_text.equalsIgnoreCase("国机系")){
                insertToYQDB(gid,"1");
            }else if(low_spinner_text.equalsIgnoreCase("国家电投系")){
                insertToYQDB(gid,"2");
            }else if(low_spinner_text.equalsIgnoreCase("南方电网")){
                insertToYQDB(gid,"3");
            }else if(low_spinner_text.equalsIgnoreCase("中核系")){
                insertToYQDB(gid,"4");
            }else if(low_spinner_text.equalsIgnoreCase("华电系")){
                insertToYQDB(gid,"5");
            }else if(low_spinner_text.equalsIgnoreCase("大唐系")){
                insertToYQDB(gid,"6");
            }else if(low_spinner_text.equalsIgnoreCase("华能系")){
                insertToYQDB(gid,"7");
            }else if(low_spinner_text.equalsIgnoreCase("兵装系")){
                insertToYQDB(gid,"8");
            }else if(low_spinner_text.equalsIgnoreCase("中石油系")){
                insertToYQDB(gid,"9");
            }else if(low_spinner_text.equalsIgnoreCase("国网系")){
                insertToYQDB(gid,"10");
            }else if(low_spinner_text.equalsIgnoreCase("国电系")){
                insertToYQDB(gid,"11");
            }else if(low_spinner_text.equalsIgnoreCase("中粮系")){
                insertToYQDB(gid,"12");
            }else if(low_spinner_text.equalsIgnoreCase("中远海运系")){
                insertToYQDB(gid,"13");
            }else if(low_spinner_text.equalsIgnoreCase("中国建材系")){
                insertToYQDB(gid,"14");
            }else if(low_spinner_text.equalsIgnoreCase("中石化系")){
                insertToYQDB(gid,"15");
            }else if(low_spinner_text.equalsIgnoreCase("中国铁路系")){
                insertToYQDB(gid,"16");
            }else if(low_spinner_text.equalsIgnoreCase("国投系")){
                insertToYQDB(gid,"17");
            }else if(low_spinner_text.equalsIgnoreCase("中科院系")){
                insertToYQDB(gid,"18");
            }else if(low_spinner_text.equalsIgnoreCase("清华系")){
                insertToYQDB(gid,"19");
            }else if(low_spinner_text.equalsIgnoreCase("中化系")){
                insertToYQDB(gid,"20");
            }else if(low_spinner_text.equalsIgnoreCase("恒天系")){
                insertToYQDB(gid,"21");
            }else if(low_spinner_text.equalsIgnoreCase("华润系")){
                insertToYQDB(gid,"22");
            }else if(low_spinner_text.equalsIgnoreCase("五矿系")){
                insertToYQDB(gid,"23");
            }else if(low_spinner_text.equalsIgnoreCase("招商局系")){
                insertToYQDB(gid,"24");
            }else if(low_spinner_text.equalsIgnoreCase("中航工业系")){
                insertToYQDB(gid,"25");
            }else if(low_spinner_text.equalsIgnoreCase("中交系")){
                insertToYQDB(gid,"26");
            }else if(low_spinner_text.equalsIgnoreCase("联通系")){
                insertToYQDB(gid,"27");
            }else if(low_spinner_text.equalsIgnoreCase("中国电科系")){
                insertToYQDB(gid,"28");
            }else if(low_spinner_text.equalsIgnoreCase("航天科技系")){
                insertToYQDB(gid,"29");
            }else if(low_spinner_text.equalsIgnoreCase("航发系")){
                insertToYQDB(gid,"30");
            }else if(low_spinner_text.equalsIgnoreCase("中国节能系")){
                insertToYQDB(gid,"31");
            }else if(low_spinner_text.equalsIgnoreCase("兵工系")){
                insertToYQDB(gid,"32");
            }else if(low_spinner_text.equalsIgnoreCase("北大系")){
                insertToYQDB(gid,"33");
            }else if(low_spinner_text.equalsIgnoreCase("中国化工系")){
                insertToYQDB(gid,"34");
            }else if(low_spinner_text.equalsIgnoreCase("中信系")){
                insertToYQDB(gid,"35");
            }else if(low_spinner_text.equalsIgnoreCase("东航系")){
                insertToYQDB(gid,"36");
            }else if(low_spinner_text.equalsIgnoreCase("宝武钢铁系")){
                insertToYQDB(gid,"37");
            }else if(low_spinner_text.equalsIgnoreCase("一汽系")){
                insertToYQDB(gid,"38");
            }else if(low_spinner_text.equalsIgnoreCase("新兴际华系")){
                insertToYQDB(gid,"39");
            }else if(low_spinner_text.equalsIgnoreCase("国药系")){
                insertToYQDB(gid,"40");
            }else if(low_spinner_text.equalsIgnoreCase("中车系")){
                insertToYQDB(gid,"41");
            }else if(low_spinner_text.equalsIgnoreCase("航天科工系")){
                insertToYQDB(gid,"42");
            }else if(low_spinner_text.equalsIgnoreCase("中国电子系")){
                insertToYQDB(gid,"43");
            }else if(low_spinner_text.equalsIgnoreCase("中船重工系")){
                insertToYQDB(gid,"44");
            }else if(low_spinner_text.equalsIgnoreCase("哈电系")){
                insertToYQDB(gid,"45");
            }else if(low_spinner_text.equalsIgnoreCase("中国诚通系")){
                insertToYQDB(gid,"46");
            }else if(low_spinner_text.equalsIgnoreCase("中船系")){
                insertToYQDB(gid,"47");
            }
        }else if(spinner_text.equalsIgnoreCase("人工智能")){
            //"基带芯片","大数据","云计算","射频器件","光通讯","芯片"
            //,"指纹识别","语音识别","人脸识别","虹膜识别","终端","智能安防", "运营商",
            // "智慧城市","车联网","智能家居","智能医疗","智慧教育","智能交通","无人驾驶",
            // "无人机"
            gid=add_gid.getText().toString();
            if(low_spinner_text.equalsIgnoreCase("基带芯片")){
                insertToAIDB(gid,"1");
            }else if(low_spinner_text.equalsIgnoreCase("大数据")){
                insertToAIDB(gid,"2");
            }else if(low_spinner_text.equalsIgnoreCase("云计算")){
                insertToAIDB(gid,"3");
            }else if(low_spinner_text.equalsIgnoreCase("射频器件")){
                insertToAIDB(gid,"4");
            }else if(low_spinner_text.equalsIgnoreCase("光通讯")){
                insertToAIDB(gid,"5");
            }else if(low_spinner_text.equalsIgnoreCase("芯片")){
                insertToAIDB(gid,"6");
            }else if(low_spinner_text.equalsIgnoreCase("指纹识别")){
                insertToAIDB(gid,"7");
            }else if(low_spinner_text.equalsIgnoreCase("语音识别")){
                insertToAIDB(gid,"8");
            }else if(low_spinner_text.equalsIgnoreCase("人脸识别")){
                insertToAIDB(gid,"9");
            }else if(low_spinner_text.equalsIgnoreCase("虹膜识别")){
                insertToAIDB(gid,"10");
            }else if(low_spinner_text.equalsIgnoreCase("终端")){
                insertToAIDB(gid,"11");
            }else if(low_spinner_text.equalsIgnoreCase("智能安防")){
                insertToAIDB(gid,"12");
            }else if(low_spinner_text.equalsIgnoreCase("运营商")){
                insertToAIDB(gid,"13");
            }else if(low_spinner_text.equalsIgnoreCase("智慧城市")){
                insertToAIDB(gid,"14");
            }else if(low_spinner_text.equalsIgnoreCase("车联网")){
                insertToAIDB(gid,"15");
            }else if(low_spinner_text.equalsIgnoreCase("智能家居")){
                insertToAIDB(gid,"16");
            }else if(low_spinner_text.equalsIgnoreCase("智能医疗")){
                insertToAIDB(gid,"17");
            }else if(low_spinner_text.equalsIgnoreCase("智慧教育")){
                insertToAIDB(gid,"18");
            }else if(low_spinner_text.equalsIgnoreCase("智能交通")){
                insertToAIDB(gid,"19");
            }else if(low_spinner_text.equalsIgnoreCase("无人驾驶")){
                insertToAIDB(gid,"20");
            }else if(low_spinner_text.equalsIgnoreCase("无人机")){
                insertToAIDB(gid,"21");
            }
        }else if(spinner_text.equalsIgnoreCase("5G")){
            //"基带芯片","大数据","云计算","射频器件","光通信","芯片"
            //,"基站天线","通讯传输设备","通信网络建设","终端","智能安防", "运营商",
            //"智慧城市","车联网","智能家居","智能医疗","智慧教育","智能交通","无人驾驶",
            //"无人机"
            gid=add_gid.getText().toString();
            if(low_spinner_text.equalsIgnoreCase("基带芯片")){
                insertToFGDB(gid,"1");
            }else if(low_spinner_text.equalsIgnoreCase("大数据")){
                insertToFGDB(gid,"2");
            }else if(low_spinner_text.equalsIgnoreCase("云计算")){
                insertToFGDB(gid,"3");
            }else if(low_spinner_text.equalsIgnoreCase("射频器件")){
                insertToFGDB(gid,"4");
            }else if(low_spinner_text.equalsIgnoreCase("光通讯")){
                insertToFGDB(gid,"5");
            }else if(low_spinner_text.equalsIgnoreCase("芯片")){
                insertToFGDB(gid,"6");
            }else if(low_spinner_text.equalsIgnoreCase("基站天线")){
                insertToFGDB(gid,"7");
            }else if(low_spinner_text.equalsIgnoreCase("通讯传输设备")){
                insertToFGDB(gid,"8");
            }else if(low_spinner_text.equalsIgnoreCase("通信网络建设")){
                insertToFGDB(gid,"9");
            }else if(low_spinner_text.equalsIgnoreCase("终端")){
                insertToFGDB(gid,"10");
            }else if(low_spinner_text.equalsIgnoreCase("智能安防")){
                insertToFGDB(gid,"11");
            }else if(low_spinner_text.equalsIgnoreCase("运营商")){
                insertToFGDB(gid,"12");
            }else if(low_spinner_text.equalsIgnoreCase("智慧城市")){
                insertToFGDB(gid,"13");
            }else if(low_spinner_text.equalsIgnoreCase("车联网")){
                insertToFGDB(gid,"14");
            }else if(low_spinner_text.equalsIgnoreCase("智能家居")){
                insertToFGDB(gid,"15");
            }else if(low_spinner_text.equalsIgnoreCase("智能医疗")){
                insertToFGDB(gid,"16");
            }else if(low_spinner_text.equalsIgnoreCase("智慧教育")){
                insertToFGDB(gid,"17");
            }else if(low_spinner_text.equalsIgnoreCase("智能交通")){
                insertToFGDB(gid,"18");
            }else if(low_spinner_text.equalsIgnoreCase("无人驾驶")){
                insertToFGDB(gid,"19");
            }else if(low_spinner_text.equalsIgnoreCase("无人机")){
                insertToFGDB(gid,"20");
            }
        }

    }

    private void insertToChinaHealthDB(String Tgid, String categoryId){
        List<healthChinaStock> stocks= LitePal.where("gid=?",Tgid).find(healthChinaStock.class);
        if(stocks.isEmpty()){
            List<StockInfoDB> stockInfo= LitePal.where("gid=?",Tgid).find(StockInfoDB.class);
            if(!stockInfo.isEmpty()){
                healthChinaStock healthChinaStock1=new healthChinaStock();
                StockInfoDB stockInfoDB1=stockInfo.get(0);
                healthChinaStock1.gid=stockInfoDB1.gid;
                healthChinaStock1.name=stockInfoDB1.name;
                healthChinaStock1.now_Price=String.valueOf(stockInfoDB1.now_Price);
                healthChinaStock1.price_change=String.valueOf(stockInfoDB1.price_change);
                healthChinaStock1.change_percent=String.valueOf(stockInfoDB1.change_percent);
                healthChinaStock1.volume=String.valueOf(stockInfoDB1.volume);
                healthChinaStock1.category=categoryId;
                healthChinaStock1.save();
                Toast.makeText(getBaseContext(),healthChinaStock1.name+"添加成功",Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(getBaseContext(),"股票代码输入错误",Toast.LENGTH_SHORT).show();
            }
        }else {
            Toast.makeText(getBaseContext(),"本股票已经添加在所选题材中",Toast.LENGTH_SHORT).show();
        }
    }

    private void insertToGreenCarDB(String Tgid, String categoryId){
        List<greenCarStock> stocks= LitePal.where("gid=?",Tgid).find(greenCarStock.class);
        if(stocks.isEmpty()){
            List<StockInfoDB> stockInfo= LitePal.where("gid=?",Tgid).find(StockInfoDB.class);
            if(!stockInfo.isEmpty()){
                greenCarStock greenCarStock1=new greenCarStock();
                StockInfoDB stockInfoDB1=stockInfo.get(0);
                greenCarStock1.gid=stockInfoDB1.gid;
                greenCarStock1.name=stockInfoDB1.name;
                greenCarStock1.now_Price=String.valueOf(stockInfoDB1.now_Price);
                greenCarStock1.price_change=String.valueOf(stockInfoDB1.price_change);
                greenCarStock1.change_percent=String.valueOf(stockInfoDB1.change_percent);
                greenCarStock1.volume=String.valueOf(stockInfoDB1.volume);
                greenCarStock1.category=categoryId;
                greenCarStock1.save();
                Toast.makeText(getBaseContext(),greenCarStock1.name+"添加成功",Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(getBaseContext(),"股票代码输入错误",Toast.LENGTH_SHORT).show();
            }
        }else {
            Toast.makeText(getBaseContext(),"本股票已经添加在所选题材中",Toast.LENGTH_SHORT).show();
        }
    }

    private void insertToXANewAreaDB(String Tgid, String categoryId){
        List<xaNewAreaStock> stocks= LitePal.where("gid=?",Tgid).find(xaNewAreaStock.class);
        if(stocks.isEmpty()){
            List<StockInfoDB> stockInfo= LitePal.where("gid=?",Tgid).find(StockInfoDB.class);
            if(!stockInfo.isEmpty()){
                xaNewAreaStock xaNewAreaStock1=new xaNewAreaStock();
                StockInfoDB stockInfoDB1=stockInfo.get(0);
                xaNewAreaStock1.gid=stockInfoDB1.gid;
                xaNewAreaStock1.name=stockInfoDB1.name;
                xaNewAreaStock1.now_Price=String.valueOf(stockInfoDB1.now_Price);
                xaNewAreaStock1.price_change=String.valueOf(stockInfoDB1.price_change);
                xaNewAreaStock1.change_percent=String.valueOf(stockInfoDB1.change_percent);
                xaNewAreaStock1.volume=String.valueOf(stockInfoDB1.volume);
                xaNewAreaStock1.category=categoryId;
                xaNewAreaStock1.save();
                Toast.makeText(getBaseContext(),xaNewAreaStock1.name+"添加成功",Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(getBaseContext(),"股票代码输入错误",Toast.LENGTH_SHORT).show();
            }
        }else {
            Toast.makeText(getBaseContext(),"本股票已经添加在所选题材中",Toast.LENGTH_SHORT).show();
        }
    }


    private void insertToSaveEnergyDB(String Tgid, String categoryId){
        List<saveEnergyStock> stocks= LitePal.where("gid=?",Tgid).find(saveEnergyStock.class);
        if(stocks.isEmpty()){
            List<StockInfoDB> stockInfo= LitePal.where("gid=?",Tgid).find(StockInfoDB.class);
            if(!stockInfo.isEmpty()){
                saveEnergyStock saveEnergyStock1=new saveEnergyStock();
                StockInfoDB stockInfoDB1=stockInfo.get(0);
                saveEnergyStock1.gid=stockInfoDB1.gid;
                saveEnergyStock1.name=stockInfoDB1.name;
                saveEnergyStock1.now_Price=String.valueOf(stockInfoDB1.now_Price);
                saveEnergyStock1.price_change=String.valueOf(stockInfoDB1.price_change);
                saveEnergyStock1.change_percent=String.valueOf(stockInfoDB1.change_percent);
                saveEnergyStock1.volume=String.valueOf(stockInfoDB1.volume);
                saveEnergyStock1.category=categoryId;
                saveEnergyStock1.save();
                Toast.makeText(getBaseContext(),saveEnergyStock1.name+"添加成功",Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(getBaseContext(),"股票代码输入错误",Toast.LENGTH_SHORT).show();
            }
        }else {
            Toast.makeText(getBaseContext(),"本股票已经添加在所选题材中",Toast.LENGTH_SHORT).show();
        }
    }


    private void insertToVRDB(String Tgid, String categoryId){
        List<vrStock> stocks= LitePal.where("gid=?",Tgid).find(vrStock.class);
        if(stocks.isEmpty()){
            List<StockInfoDB> stockInfo= LitePal.where("gid=?",Tgid).find(StockInfoDB.class);
            if(!stockInfo.isEmpty()){
                vrStock vrStock1=new vrStock();
                StockInfoDB stockInfoDB1=stockInfo.get(0);
                vrStock1.gid=stockInfoDB1.gid;
                vrStock1.name=stockInfoDB1.name;
                vrStock1.now_Price=String.valueOf(stockInfoDB1.now_Price);
                vrStock1.price_change=String.valueOf(stockInfoDB1.price_change);
                vrStock1.change_percent=String.valueOf(stockInfoDB1.change_percent);
                vrStock1.volume=String.valueOf(stockInfoDB1.volume);
                vrStock1.category=categoryId;
                vrStock1.save();
                Toast.makeText(getBaseContext(),vrStock1.name+"添加成功",Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(getBaseContext(),"股票代码输入错误",Toast.LENGTH_SHORT).show();
            }
        }else {
            Toast.makeText(getBaseContext(),"本股票已经添加在所选题材中",Toast.LENGTH_SHORT).show();
        }
    }

    private void insertToOneRoadDB(String Tgid, String categoryId){
        List<oneRoadStock> stocks= LitePal.where("gid=?",Tgid).find(oneRoadStock.class);
        if(stocks.isEmpty()){
            List<StockInfoDB> stockInfo= LitePal.where("gid=?",Tgid).find(StockInfoDB.class);
            if(!stockInfo.isEmpty()){
                oneRoadStock oneRoadStock1=new oneRoadStock();
                StockInfoDB stockInfoDB1=stockInfo.get(0);
                oneRoadStock1.gid=stockInfoDB1.gid;
                oneRoadStock1.name=stockInfoDB1.name;
                oneRoadStock1.now_Price=String.valueOf(stockInfoDB1.now_Price);
                oneRoadStock1.price_change=String.valueOf(stockInfoDB1.price_change);
                oneRoadStock1.change_percent=String.valueOf(stockInfoDB1.change_percent);
                oneRoadStock1.volume=String.valueOf(stockInfoDB1.volume);
                oneRoadStock1.category=categoryId;
                oneRoadStock1.save();
                Toast.makeText(getBaseContext(),oneRoadStock1.name+"添加成功",Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(getBaseContext(),"股票代码输入错误",Toast.LENGTH_SHORT).show();
            }
        }else {
            Toast.makeText(getBaseContext(),"本股票已经添加在所选题材中",Toast.LENGTH_SHORT).show();
        }
    }

    private void insertToYQDB(String Tgid, String categoryId){
        List<yqStock> stocks= LitePal.where("gid=?",Tgid).find(yqStock.class);
        if(stocks.isEmpty()){
            List<StockInfoDB> stockInfo= LitePal.where("gid=?",Tgid).find(StockInfoDB.class);
            if(!stockInfo.isEmpty()){
                yqStock yqStock1=new yqStock();
                StockInfoDB stockInfoDB1=stockInfo.get(0);
                yqStock1.gid=stockInfoDB1.gid;
                yqStock1.name=stockInfoDB1.name;
                yqStock1.now_Price=String.valueOf(stockInfoDB1.now_Price);
                yqStock1.price_change=String.valueOf(stockInfoDB1.price_change);
                yqStock1.change_percent=String.valueOf(stockInfoDB1.change_percent);
                yqStock1.volume=String.valueOf(stockInfoDB1.volume);
                yqStock1.category=categoryId;
                yqStock1.save();
                Toast.makeText(getBaseContext(),yqStock1.name+"添加成功",Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(getBaseContext(),"股票代码输入错误",Toast.LENGTH_SHORT).show();
            }
        }else {
            Toast.makeText(getBaseContext(),"本股票已经添加在所选题材中",Toast.LENGTH_SHORT).show();
        }
    }

    private void insertToAIDB(String Tgid, String categoryId){
        List<aiStock> stocks= LitePal.where("gid=?",Tgid).find(aiStock.class);
        if(stocks.isEmpty()){
            List<StockInfoDB> stockInfo= LitePal.where("gid=?",Tgid).find(StockInfoDB.class);
            if(!stockInfo.isEmpty()){
                aiStock aiStock1=new aiStock();
                StockInfoDB stockInfoDB1=stockInfo.get(0);
                aiStock1.gid=stockInfoDB1.gid;
                aiStock1.name=stockInfoDB1.name;
                aiStock1.now_Price=String.valueOf(stockInfoDB1.now_Price);
                aiStock1.price_change=String.valueOf(stockInfoDB1.price_change);
                aiStock1.change_percent=String.valueOf(stockInfoDB1.change_percent);
                aiStock1.volume=String.valueOf(stockInfoDB1.volume);
                aiStock1.category=categoryId;
                aiStock1.save();
                Toast.makeText(getBaseContext(),aiStock1.name+"添加成功",Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(getBaseContext(),"股票代码输入错误",Toast.LENGTH_SHORT).show();
            }
        }else {
            Toast.makeText(getBaseContext(),"本股票已经添加在所选题材中",Toast.LENGTH_SHORT).show();
        }
    }

    private void insertToFGDB(String Tgid, String categoryId){
        List<fgStock> stocks= LitePal.where("gid=?",Tgid).find(fgStock.class);
        if(stocks.isEmpty()){
            List<StockInfoDB> stockInfo= LitePal.where("gid=?",Tgid).find(StockInfoDB.class);
            if(!stockInfo.isEmpty()){
                fgStock fgStock1=new fgStock();
                StockInfoDB stockInfoDB1=stockInfo.get(0);
                fgStock1.gid=stockInfoDB1.gid;
                fgStock1.name=stockInfoDB1.name;
                fgStock1.now_Price=String.valueOf(stockInfoDB1.now_Price);
                fgStock1.price_change=String.valueOf(stockInfoDB1.price_change);
                fgStock1.change_percent=String.valueOf(stockInfoDB1.change_percent);
                fgStock1.volume=String.valueOf(stockInfoDB1.volume);
                fgStock1.category=categoryId;
                fgStock1.save();
                Toast.makeText(getBaseContext(),fgStock1.name+"添加成功",Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(getBaseContext(),"股票代码输入错误",Toast.LENGTH_SHORT).show();
            }
        }else {
            Toast.makeText(getBaseContext(),"本股票已经添加在所选题材中",Toast.LENGTH_SHORT).show();
        }
    }

}
