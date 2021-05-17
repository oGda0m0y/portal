<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; utf-8">
    <title>我的中心</title>
    <%@ include file="../../static/top.jsp"%>
 <link href="/web/res/css/bootstrap-switch/bootstrap-switch.min.css" rel="stylesheet">
</head>
<body class="sticky-header">

<section> <!-- left side start--> <%@ include
        file="../../static/menu.jsp"%> <!-- left side end-->

    <!-- main content start-->
    <div class="main-content">

        <!-- header section start-->


        <%@ include file="../../static/header.jsp"%>


        <!-- header section end-->

        <!-- page heading start-->

        <!-- page heading end-->

        <!--body wrapper start-->
        <div class="wrapper">
        <div class="page-heading">
				<ul class="breadcrumb">
					<li><a href="#">我的采集</a></li>
					<li class="active">采集列表</li>
				</ul>
			</div>
            <div class="mail-box">
                <aside class="mail-nav mail-nav-bg-color"> <header
                        class="header">
                    <h4>
                        我的采集列表<span id="tmplsize"
                                    class="label label-danger pull-right inbox-notification"></span>
                    </h4>
                </header>
                    <div class="mail-nav-body">

                        <ul id="mytmplist"
                            class="nav nav-pills nav-stacked mail-navigation">
                        </ul>


                    </div>

                </aside>
                <section class="mail-box-info"> <header class="header">

                    <div class="btn-toolbar">
                        <h4 class="pull-left">采集任务配置</h4>
                    </div>
                </header> <section class="mail-list" style="height:auto">
                    <div class="login-wrap template_setting" mes=""
                         style="padding-top: 20px; padding-left: 0px;">
                        <div class="form-group col-sm-12">
                            <label for="username" class="col-sm-2"
                                   style="font-size: 18px; padding-top: 5px;">模板名称</label>

                            <div class="col-sm-10">
                                <input type="text" class="form-control" id="template_name"
                                       value="">
                            </div>
                        </div>
                        <div class="form-group col-sm-12">
                            <label for="username" class="col-sm-2"
                                   style="font-size: 18px; padding-top: 5px;">采集网址</label>

                            <div class="form-group col-sm-10">
							<textarea class="form-control" rows=2 id="url"
                                      disabled="disabled"></textarea>
                            </div>
                        </div>
                        <div class="form-group col-sm-12">
                            <label for="username" class="col-sm-2"
                                   style="font-size: 18px; padding-top: 5px;">翻页配置</label>

                            <div class="form-group col-sm-10">
                               <input type="checkbox" id="my-page" >
                            </div>
                        </div>
                        <div id="prule" class="form-group col-sm-12">
                            <label for="username" class="col-sm-2"
                                   style="font-size: 18px; padding-top: 5px;">配置规则</label>

                            <div class="form-group col-sm-10">
                                <textarea class="form-control" rows=3 id="page_rule"></textarea>
                            </div>
                        </div>
                        <div id="pconf" class="form-group col-sm-12">
                            <label for="username" class="col-sm-2"
                                   style="font-size: 18px; padding-top: 5px;">页码配置</label>

                            <div class="col-sm-8">
                                <label for="username" class="col-sm-2 control-label"
                                       style="padding-top: 5px;">页码配置</label>

                                <div class="form-group col-sm-4">
                                    <select id="minPage" class="form-control">
                                        <option value=1>1</option>
                                        <option value=2>2</option>
                                        <option value=3>3</option>
                                        <option value=4>4</option>
                                        <option value=5>5</option>
                                        <option value=6>6</option>
                                        <option value=7>7</option>
                                        <option value=8>8</option>
                                        <option value=9>9</option>
                                        <option value=10>10</option>
                                        <option value=11>11</option>
                                        <option value=12>12</option>
                                        <option value=13>13</option>
                                        <option value=14>14</option>
                                        <option value=15>15</option>
                                        <option value=16>16</option>
                                        <option value=17>17</option>
                                        <option value=18>18</option>
                                        <option value=19>19</option>
                                        <option value=20>20</option>
                                        <option value=21>21</option>
                                        <option value=22>22</option>
                                        <option value=23>23</option>
                                        <option value=24>24</option>
                                        <option value=25>25</option>
                                        <option value=26>26</option>
                                        <option value=27>27</option>
                                        <option value=28>28</option>
                                        <option value=29>29</option>
                                        <option value=30>30</option>
                                        <option value=31>31</option>
                                        <option value=32>32</option>
                                        <option value=33>33</option>
                                        <option value=34>34</option>
                                        <option value=35>35</option>
                                        <option value=36>36</option>
                                        <option value=37>37</option>
                                        <option value=38>38</option>
                                        <option value=39>39</option>
                                        <option value=40>40</option>
                                        <option value=41>41</option>
                                        <option value=42>42</option>
                                        <option value=43>43</option>
                                        <option value=44>44</option>
                                        <option value=45>45</option>
                                        <option value=46>46</option>
                                        <option value=47>47</option>
                                        <option value=48>48</option>
                                        <option value=49>49</option>
                                        <option value=50>50</option>
                                        <option value=51>51</option>
                                        <option value=52>52</option>
                                        <option value=53>53</option>
                                        <option value=54>54</option>
                                        <option value=55>55</option>
                                        <option value=56>56</option>
                                        <option value=57>57</option>
                                        <option value=58>58</option>
                                        <option value=59>59</option>
                                        <option value=60>60</option>
                                        <option value=61>61</option>
                                        <option value=62>62</option>
                                        <option value=63>63</option>
                                        <option value=64>64</option>
                                        <option value=65>65</option>
                                        <option value=66>66</option>
                                        <option value=67>67</option>
                                        <option value=68>68</option>
                                        <option value=69>69</option>
                                        <option value=70>70</option>
                                        <option value=71>71</option>
                                        <option value=72>72</option>
                                        <option value=73>73</option>
                                        <option value=74>74</option>
                                        <option value=75>75</option>
                                        <option value=76>76</option>
                                        <option value=77>77</option>
                                        <option value=78>78</option>
                                        <option value=79>79</option>
                                        <option value=80>80</option>
                                        <option value=81>81</option>
                                        <option value=82>82</option>
                                        <option value=83>83</option>
                                        <option value=84>84</option>
                                        <option value=85>85</option>
                                        <option value=86>86</option>
                                        <option value=87>87</option>
                                        <option value=88>88</option>
                                        <option value=89>89</option>
                                        <option value=90>90</option>
                                        <option value=91>91</option>
                                        <option value=92>92</option>
                                        <option value=93>93</option>
                                        <option value=94>94</option>
                                        <option value=95>95</option>
                                        <option value=96>96</option>
                                        <option value=97>97</option>
                                        <option value=98>98</option>
                                        <option value=99>99</option>
                                        <option value=100>100</option>
                                    </select>
                                </div>
                                <label for="username" class="col-sm-2 control-label"
                                       style="padding-top: 5px;">最大页码</label>

                                <div class="form-group col-sm-4">
                                    <select id="maxPage" class="form-control">
                                        <option value=1>1</option>
                                        <option value=2>2</option>
                                        <option value=3>3</option>
                                        <option value=4>4</option>
                                        <option value=5>5</option>
                                        <option value=6>6</option>
                                        <option value=7>7</option>
                                        <option value=8>8</option>
                                        <option value=9>9</option>
                                        <option value=10 selected>10</option>
                                        <option value=11>11</option>
                                        <option value=12>12</option>
                                        <option value=13>13</option>
                                        <option value=14>14</option>
                                        <option value=15>15</option>
                                        <option value=16>16</option>
                                        <option value=17>17</option>
                                        <option value=18>18</option>
                                        <option value=19>19</option>
                                        <option value=20>20</option>
                                        <option value=21>21</option>
                                        <option value=22>22</option>
                                        <option value=23>23</option>
                                        <option value=24>24</option>
                                        <option value=25>25</option>
                                        <option value=26>26</option>
                                        <option value=27>27</option>
                                        <option value=28>28</option>
                                        <option value=29>29</option>
                                        <option value=30>30</option>
                                        <option value=31>31</option>
                                        <option value=32>32</option>
                                        <option value=33>33</option>
                                        <option value=34>34</option>
                                        <option value=35>35</option>
                                        <option value=36>36</option>
                                        <option value=37>37</option>
                                        <option value=38>38</option>
                                        <option value=39>39</option>
                                        <option value=40>40</option>
                                        <option value=41>41</option>
                                        <option value=42>42</option>
                                        <option value=43>43</option>
                                        <option value=44>44</option>
                                        <option value=45>45</option>
                                        <option value=46>46</option>
                                        <option value=47>47</option>
                                        <option value=48>48</option>
                                        <option value=49>49</option>
                                        <option value=50>50</option>
                                        <option value=51>51</option>
                                        <option value=52>52</option>
                                        <option value=53>53</option>
                                        <option value=54>54</option>
                                        <option value=55>55</option>
                                        <option value=56>56</option>
                                        <option value=57>57</option>
                                        <option value=58>58</option>
                                        <option value=59>59</option>
                                        <option value=60>60</option>
                                        <option value=61>61</option>
                                        <option value=62>62</option>
                                        <option value=63>63</option>
                                        <option value=64>64</option>
                                        <option value=65>65</option>
                                        <option value=66>66</option>
                                        <option value=67>67</option>
                                        <option value=68>68</option>
                                        <option value=69>69</option>
                                        <option value=70>70</option>
                                        <option value=71>71</option>
                                        <option value=72>72</option>
                                        <option value=73>73</option>
                                        <option value=74>74</option>
                                        <option value=75>75</option>
                                        <option value=76>76</option>
                                        <option value=77>77</option>
                                        <option value=78>78</option>
                                        <option value=79>79</option>
                                        <option value=80>80</option>
                                        <option value=81>81</option>
                                        <option value=82>82</option>
                                        <option value=83>83</option>
                                        <option value=84>84</option>
                                        <option value=85>85</option>
                                        <option value=86>86</option>
                                        <option value=87>87</option>
                                        <option value=88>88</option>
                                        <option value=89>89</option>
                                        <option value=90>90</option>
                                        <option value=91>91</option>
                                        <option value=92>92</option>
                                        <option value=93>93</option>
                                        <option value=94>94</option>
                                        <option value=95>95</option>
                                        <option value=96>96</option>
                                        <option value=97>97</option>
                                        <option value=98>98</option>
                                        <option value=99>99</option>
                                        <option value=100>100</option>
                                    </select>
                                </div>
                            </div>
                            <div class="col-sm-2 "></div>

                        </div>
                        <div class="form-group col-sm-12">


                            <div class="panel panel-primary">
                                <div class="panel-heading">
                                    <h3 class="panel-title">任务配置</h3>
                                </div>
                                <div class="panel-body">
                                    <div class="row">


                                        <div class="col-sm-12 ">


                                            <form class="form-horizontal bucket-form" method="get">
                                                <div class="form-group">
                                                    <label for="username" class="col-sm-2"
                                                           style="font-size: 18px; padding-top: 5px;">任务配置</label>

                                                    <div class="col-sm-10 icheck ">
                                                        <div class="square">
                                                            <div class="radio ">
                                                                <input jobindex="now" type="radio" checked
                                                                       name="job-radio"> <label>立即执行</label>
                                                            </div>
                                                        </div>
                                                        <div class="square">
                                                            <div class="radio ">
                                                                <input jobindex="once" type="radio" name="job-radio">
                                                                <label>一次性 </label>
                                                            </div>
                                                        </div>
                                                        <div class="square" style="display: none">
                                                            <div class="radio ">
                                                                <input jobindex="min" type="radio" name="job-radio">
                                                                <label>按分</label>
                                                            </div>
                                                        </div>
                                                        <div class="square" style="display: none">
                                                            <div class="radio ">
                                                                <input jobindex="hour" type="radio" name="job-radio">
                                                                <label>按小时 </label>
                                                            </div>
                                                        </div>

                                                        <div class="square">
                                                            <div class="radio ">
                                                                <input jobindex="day" type="radio" name="job-radio">
                                                                <label>按天 </label>
                                                            </div>
                                                        </div>
                                                        <div class="square" style="display: none">
                                                            <div class="radio ">
                                                                <input jobindex="week" type="radio" name="job-radio">
                                                                <label>按周 </label>
                                                            </div>
                                                        </div>
                                                        <div class="square" style="display: none">
                                                            <div class="radio ">
                                                                <input jobindex="month" type="radio" name="job-radio">
                                                                <label>按月 </label>
                                                            </div>
                                                        </div>

                                                    </div>
                                                </div>
                                            </form>


                                            <form id="range_times" class="form-horizontal" role="form">
                                                <div class="form-group">
                                                    <label for="username" class="col-sm-2"
                                                           style="font-size: 18px; padding-top: 5px;">时间范围</label>

                                                    <div class="col-sm-8">
                                                        <label id="start_text" for="label"
                                                               class="col-sm-2 control-label">开始时间</label>

                                                        <div class="col-sm-4 ">
                                                            <input type="text" class="form-control " id="start_date"
                                                                   onclick="WdatePicker({isShowClear:false,readOnly:true,skin:'twoer'})"
                                                                   value=""> <input type="text"
                                                                                    class="form-control " id="start_date1"
                                                                                    onclick="WdatePicker({skin:'twoer',dateFmt:'yyyy-MM-dd HH:mm'})"
                                                                                    value="">
                                                        </div>

                                                        <label for="label" id="end_text"
                                                               class="col-sm-2 control-label">结束时间</label>

                                                        <div class="col-sm-4 ">
                                                            <input type="text" class="form-control" id="end_date"
                                                                   onclick="WdatePicker({isShowClear:false,readOnly:true,skin:'twoer'})"
                                                                   value="">
                                                        </div>
                                                    </div>

                                                    <div class="col-sm-2 "></div>
                                                </div>

                                            </form>
                                            <form id="byweek" class="form-horizontal bucket-form"
                                                  method="get">
                                                <div class="form-group">
                                                    <label for="username" class="col-sm-2"
                                                           style="font-size: 18px; padding-top: 5px;">按周</label>

                                                    <div class="col-sm-10 icheck ">

                                                        <div class="square">
                                                            <div class="radio ">
                                                                <input type="checkbox"> <label>周一</label>
                                                            </div>
                                                        </div>
                                                        <div class="square">
                                                            <div class="radio ">
                                                                <input type="checkbox"> <label>周二</label>
                                                            </div>
                                                        </div>

                                                        <div class="square">
                                                            <div class="radio ">
                                                                <input type="checkbox"> <label>周三</label>
                                                            </div>
                                                        </div>
                                                        <div class="square">
                                                            <div class="radio ">
                                                                <input type="checkbox"> <label>周四</label>
                                                            </div>
                                                        </div>
                                                        <div class="square">
                                                            <div class="radio ">
                                                                <input type="checkbox"> <label>周五</label>
                                                            </div>
                                                        </div>
                                                        <div class="square">
                                                            <div class="radio ">
                                                                <input type="checkbox"> <label>周六</label>
                                                            </div>
                                                        </div>
                                                        <div class="square">
                                                            <div class="radio ">
                                                                <input type="checkbox" > <label>周日</label>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </form>


                                            <form id="bymonth" class="form-horizontal bucket-form"
                                                  method="get">
                                                <div class="form-group">
                                                    <label class="col-sm-2"
                                                           style="font-size: 18px; padding-top: 5px;">按月</label>

                                                    <div class="col-sm-4">
                                                        <label id="日期" for="label" class="col-sm-4 control-label">开始日期</label>

                                                        <div class="col-sm-8 ">
                                                            <select class="form-control ">
                                                                <option>1日</option>
                                                                <option>2日</option>
                                                                <option>3日</option>
                                                                <option>4日</option>
                                                                <option>5日</option>
                                                                <option>6日</option>
                                                                <option>7日</option>
                                                                <option>8日</option>
                                                                <option>9日</option>
                                                                <option>10日</option>
                                                                <option>11日</option>
                                                                <option>12日</option>
                                                                <option>13日</option>
                                                                <option>14日</option>
                                                                <option>15日</option>
                                                                <option>16日</option>
                                                                <option>17日</option>
                                                                <option>18日</option>
                                                                <option>19日</option>
                                                                <option>20日</option>
                                                                <option>21日</option>
                                                                <option>22日</option>
                                                                <option>23日</option>
                                                                <option>24日</option>
                                                                <option>25日</option>
                                                                <option>26日</option>
                                                                <option>27日</option>
                                                                <option>28日</option>
                                                                <option>29日</option>
                                                                <option>30日</option>
                                                                <option>31日</option>
                                                            </select>
                                                        </div>

                                                    </div>

                                                    <div class="col-sm-4">
                                                        <label for="label" class="col-sm-4 control-label">启动时间</label>

                                                        <div class="col-sm-8 ">
                                                            <input type="text" class="form-control" id="start_time1"
                                                                   onclick="WdatePicker({skin:'twoer',dateFmt:'H'})"
                                                                   value="">
                                                        </div>
                                                    </div>

                                                </div>
                                            </form>
                                            <form id="bymin" class="form-horizontal bucket-form"
                                                  method="get">
                                                <div class="form-group">
                                                    <label class="col-sm-2"
                                                           style="font-size: 18px; padding-top: 5px;">按分钟</label>

                                                    <div class="col-sm-8">
                                                        <label for="label" class="col-sm-2 control-label">每</label>

                                                        <div class="col-sm-2 ">
                                                            <select class="form-control ">
                                                                <option>1</option>
                                                                <option>2</option>
                                                                <option>5</option>
                                                                <option>10</option>
                                                                <option>15</option>
                                                                <option>20</option>
                                                                <option>30</option>
                                                                <option>40</option>
                                                                <option>50</option>
                                                            </select>
                                                        </div>
                                                        <label for="label" class="col-sm-1 control-label">分</label>
                                                    </div>
                                                </div>
                                            </form>

                                            <form id="byhour" class="form-horizontal bucket-form"
                                                  method="get">
                                                <div class="form-group">
                                                    <label class="col-sm-2"
                                                           style="font-size: 18px; padding-top: 5px;">按时</label>

                                                    <div class="col-sm-8">
                                                        <label for="label" class="col-sm-2 control-label">每</label>

                                                        <div class="col-sm-2 ">
                                                            <select class="form-control ">
                                                                <option>1</option>
                                                                <option>2</option>
                                                                <option>3</option>
                                                                <option>4</option>
                                                                <option>5</option>
                                                                <option>6</option>
                                                                <option>7</option>
                                                                <option>8</option>
                                                                <option>9</option>
                                                                <option>10</option>
                                                                <option>11</option>
                                                                <option>12</option>
                                                                <option>13</option>
                                                                <option>14</option>
                                                                <option>15</option>
                                                                <option>16</option>
                                                                <option>17</option>
                                                                <option>18</option>
                                                                <option>19</option>
                                                                <option>20</option>
                                                                <option>21</option>
                                                                <option>22</option>
                                                                <option>23</option>
                                                                <option>24</option>
                                                            </select>
                                                        </div>
                                                        <label for="label" class="col-sm-1 control-label">时</label>

                                                    </div>

                                                </div>
                                            </form>
                                            <form id="prehour" class="form-horizontal bucket-form"
                                                  method="get">
                                                <div class="form-group">
                                                    <label class="col-sm-2"
                                                           style="font-size: 18px; padding-top: 5px;">按时</label>

                                                    <div class="col-sm-8">
                                                        <label for="label" class="col-sm-2 control-label">启动时间</label>

                                                        <div class="col-sm-4 ">
                                                            <input type="text" class="form-control" id="start_time"
                                                                   onclick="WdatePicker({skin:'twoer',dateFmt:'H'})"
                                                                   value="">
                                                        </div>
                                                    </div>
                                                </div>
                                            </form>
                                        </div>


                                    </div>

                                </div>
                            </div>
                            <div class="compose-btn pull-right">
                                <a class="btn btn-sm btn-primary" href="javascript:void(0)"
                                   id="saveBtn"><i class="fa fa-check-circle-o"></i> 保存配置</a> <a
                                    class="btn btn-sm btn-primary" href="javascript:void(0)"
                                    id="startJobBtn"><i class="fa fa-play"></i> 启动任务</a>

                            </div>

                        </div>


                    </div>
                </section> </section>

            </div>
        </div>
        <!--body wrapper end-->


        <!--footer section start-->
        <%@ include file="../../static/footer.jsp"%>
      
        <script src="/web/res/js/bootstrap-switch/bootstrap-switch.js" ></script>
        <!--footer section end-->


    </div>
    <!-- main content end--> </section>

<script type="text/javascript">
    var tplList = ${tmpList};
    $(document).ready(function() {
    	$('#my-page').on('switchChange.bootstrapSwitch', function (event,state) {  
    		if(state){
        	   $("#prule").show();
        	   $("#pconf").show();
            }else{
        	   $("#prule").hide();
        	   $("#pconf").hide(); 
            }
        });  
        initialization();
        var li = "";
        for (var i = 0; i < tplList.length; i++) {
            li = li
                + "<li id='li"
                + i
                + "' onclick='load("
                + tplList[i].id
                + ")' style='overflow:hidden;white-space:nowrap; text-overflow:ellipsis;'><a href='javascript:void(0)'> <i class='fa fa-cogs'></i>"
                + tplList[i].template_name
                + "</a></li>";
        }
        $("#mytmplist").html(li);
        //初始化分
        var mli = "";
        for (var i = 0; i < 60; i++) {
            mli = mli + "<option value='" + i + "'>" + i+ "</option>";
        }
        $("#mins").html(mli);
        //初始化时
        var hli = "";
        for (var i = 0; i < 24; i++) {
            hli = hli + "<option value='" + i + "'>" + i+ "</option>";
        }
        $("#hours").html(hli);
        //初始化天
        var dli = "";
        for (var i = 0; i < 31; i++) {
            dli = dli + "<option value='" + i + "'>" + i	+ "</option>";
        }
        $("#days").html(dli);
        //初始化月
        var mli = "";
        for (var i = 0; i < 13; i++) {
            mli = mli + "<option value='" + i + "'>" + i	+ "</option>";
        }
        $("#month").html(mli);
        $("#li0").click();
        $("#tmplsize").text(tplList.length);
      
        
       
       
        
    });
    
   
    function load(id) {
        var list = $("#mytmplist").find("li");
        for (var i = 0; i < list.length; i++) {
            list.eq(i).removeClass("active");
        }
        $("li[onclick='load(" + id + ")']").addClass("active");
        $(".template_setting").attr("mes", id);
        for (var i = 0; i < tplList.length; i++) {
            if (id == tplList[i].id) {
				if(tplList[i].isconf==null||tplList[i].isconf==''||tplList[i]==undefined||tplList[i]==0){
					$('#my-page').bootstrapSwitch("state",false);
					/**
					$('#my-page').bootstrapSwitch("state",false,{  
						  
				       
				        onSwitchChange:function(event,state){  
				           if(state){
				        	   $("#prule").show();
				        	   $("#pconf").show();
				           }else{
				        	   $("#prule").hide();
				        	   $("#pconf").hide(); 
				           }
				        }  
				    }) 
				    **/
				    $("#prule").hide();
		        	$("#pconf").hide();
				}else{
					$('#my-page').bootstrapSwitch("state",true);
					/**
					$('#my-page').bootstrapSwitch({  
				        onSwitchChange:function(event,state){  
				           if(state){
				        	   $("#prule").show();
				        	   $("#pconf").show();
				           }else{
				        	   $("#prule").hide();
				        	   $("#pconf").hide(); 
				           }
				        }  
				    }) 
				    **/
				    $("#prule").show();
	        	    $("#pconf").show();
				}
                $("#template_name").val(tplList[i].template_name);
                $("#url").val(tplList[i].url);
                if(tplList[i].page_rule==null||tplList[i].page_rule==''||tplList[i].page_rule==undefined){
                    $("#page_rule").val(tplList[i].url);
                }else{
                    $("#page_rule").val(tplList[i].page_rule);
                }

               // var bylists=$(".iradio_square");
               
                   
                   
                      
                     
                       
            initialization();
            var newDate =new Date(tplList[i].start_date);
            var starttime=timetoString(newDate);
            if(tplList[i].byType=="now"){
            	$('input[jobindex="now"]').iCheck('check');  
            }
               else if(tplList[i].byType=="once"){
                   $("#start_date1").val(starttime.substring(0,16));
                   $('input[jobindex="once"]').iCheck('check'); 
               }else{
               	
                   var endtime=timetoString(new Date(tplList[i].end_date));
                   var task_rule=tplList[i].task_rule;
                   var time_list=task_rule.split(" ");
                   if(tplList[i].byType=="min"){
                       $("#start_date").val(starttime.substring(0,10));
                       $("#end_date").val(endtime.substring(0,10));
                       var min=time_list[1].split("\/")[1];
                       var min_list=$("#bymin").find("select option");
                       for(var m=0;m<min_list.length;m++){
                           var mjudge=min_list[m].value;
                           if(mjudge==min){
                               min_list.eq(m).attr("selected","");
                               break;
                           }
                       }
                   }else if(tplList[i].byType=="hour"){
                       $("#start_date").val(starttime.substring(0,10));
                       $("#end_date").val(endtime.substring(0,10));
                       var hour=time_list[2].split("\/")[1];
                       var hour_list=$("#byhour").find("select option");
                       for(var m=0;m<hour_list.length;m++){
                           var hjudge=hour_list[m].value;
                           if(hjudge==hour){
                               hour_list.eq(m).attr("selected","");
                               break;
                           }
                       }
                   }else if(tplList[i].byType=="day"){
                   	$('input[jobindex="day"]').iCheck('check');
                       $("#start_date").val(starttime.substring(0,10));
                       $("#end_date").val(endtime.substring(0,10));
                       $("#start_time").val(time_list[2]);
                   }else if(tplList[i].byType=="week"){
                       $("#start_date").val(starttime.substring(0,10));
                       $("#end_date").val(endtime.substring(0,10));
                       var week_list=$("#byweek .radio");
                       var weeks=time_list[5].replace("MON","周一").replace("TUE","周二").replace("WED","周三").replace("THU","周四").replace("FRI","周五").replace("SAT","周六").replace("SUN","周日");
                       if(weeks!=null||weeks!=""){
                           for(var m=0;m<week_list.length;m++){
                               var wjudge=week_list[m].children[1].innerHTML;
                               if(weeks.indexOf(wjudge)>=0){
                                   week_list.eq(m).find("input").attr("checked","");
                               }
                           }
                           initialization();
                       }
                       $("#start_time").val(time_list[2]);
                   }else if(tplList[i].byType=="month"){
                       $("#start_date").val(starttime.substring(0,10));
                       $("#end_date").val(endtime.substring(0,10));
                       var hour=time_list[2];
                       var day=time_list[3];
                       var sday=$("#bymonth").find("select option");
                       for(m=0;m<sday.length;m++){
                           var mjudge=sday[m].value.replace("日","");
                           if(mjudge==day){
                               sday.eq(m).attr("selected","");
                               break;
                           }
                       }
                       $("#start_time1").val(hour);
                   }
               }
         
                           
                        
                    
                

            }
            $(".template_setting").html();
        }
    }
    $("#saveBtn").click(function() {
        var tid = $(".template_setting")[0].attributes[1].nodeValue;
        var template_name = $("#template_name").val();
        var page_rule = $("#page_rule").val();

        var minPage = $("#minPage").val();
        var maxPage = $("#maxPage").val();
        var s_cycle = $(".iradio_square");
        var start_date = null;
        var end_date = null;
        var task_cycle = "";
        for (var i = 0; i < s_cycle.length; i++) {
            var judge = s_cycle[i].attributes[0].nodeValue;
            if (judge.indexOf("checked") >= 0) {
                var select = s_cycle[i].children[0].attributes[0].nodeValue;
                if (select == "once") {
                    start_date = $("#start_date1").val();
                    var date = start_date.split(" ")[0];
                    var time = start_date.split(" ")[1]
                    var s_date = date.split("-");
                    var s_time = time.split(":");
                    task_cycle = "0 " + parseInt(s_time[1])
                        + " " + parseInt(s_time[0])
                        + " " + parseInt(s_date[2])
                        + " " + parseInt(s_date[1])
                        + " ? " + parseInt(s_date[0]);
                } else if (select == "min") {
                    start_date = $("#start_date").val();
                    end_date = $("#end_date").val();
                    var min = $("#bymin").find("select")
                        .val();
                    task_cycle = "0 0\/" + min
                        + " * * * * * ?";
                } else if (select == "hour") {
                    start_date = $("#start_date").val();
                    end_date = $("#end_date").val();
                    var hour = $("#byhour").find("select")
                        .val();
                    task_cycle = "0 0 0\/" + hour
                        + " * * * * ?";
                } else if (select == "day") {
                    start_date = $("#start_date").val();
                    end_date = $("#end_date").val();
                    var s_time = $("#byweek").find(
                        ".square");
                    var time = $("#start_time").val();
                    task_cycle = "0 0 " + parseInt(time)+ " * * ?";
                } else if (select == "week") {
                    start_date = $("#start_date").val();
                    end_date = $("#end_date").val();
                    var s_week = "";
                    var weeks = $("#byweek").find(".radio");
                    for (var i = 0; i < weeks.length; i++) {
                        var judge = weeks[i].children[0].attributes[0].nodeValue;
                        if (judge.indexOf("checked") >= 0) {
                            switch (i) {
                                case 0:
                                    s_week = s_week + "MON,";
                                    break;
                                case 1:
                                    s_week = s_week + "TUE,";
                                    break;
                                case 2:
                                    s_week = s_week + "WED,";
                                    break;
                                case 3:
                                    s_week = s_week + "THU,";
                                    break;
                                case 4:
                                    s_week = s_week + "FRI,";
                                    break;
                                case 5:
                                    s_week = s_week + "SAT,";
                                    break;
                                case 6:
                                    s_week = s_week + "SUN,";
                                    break;
                            }
                        }
                    }
                    //删除最后一个,
                    s_week = s_week.substring(0,
                        s_week.length - 1);
                    var time = $("#start_time").val();
                    task_cycle = "0 0 " + parseInt(time)
                        + " ? * " + s_week;
                } else if (select == "month") {
                    start_date = $("#start_date").val();
                    end_date = $("#end_date").val();
                    var month = $("#bymonth")
                        .find("select").val().replace(
                            "日", "");
                    var time = $("#start_time1").val()
                    task_cycle = "0 0 " + parseInt(time)
                        + " " + month + " * ?";
                }
                break;
            }
        }

        var byType = $("input[name='job-radio']:checked")
            .attr("jobindex");

        var isconf = $("#my-page").bootstrapSwitch('state');
        var ispage = 0;
        if(isconf){
        	ispage = 1;
        }
        

        $.post("/action/my/m/saveTemplateConfig", {
            "tid" : tid,
            "minPage" : minPage,
            "maxPage" : maxPage,
            "template_name" : template_name,
            "page_rule" : page_rule,
            "byType" : byType,
            "ispage":ispage,
            "start_date" : start_date,
            "end_date" : end_date,
            "task_cycle" : task_cycle
        }, function(result) {
            if (result.code == 200) {
                layer.msg("保存成功");
            } else {
                layer.msg(result.msg);
            }
        })
    })
    $("#startJobBtn").click(function() {
        var tid = $(".template_setting")[0].attributes[1].nodeValue;
        $.blockUI({ message: '<h1>请稍等...</h1> ', css: { width: '200px', height: '100px' } });
        $.post("/action/my/m/startTmplJob", {
            "tid" : tid
        }, function(result) {
        	$.unblockUI();
            if (result.code == 200) {
                layer.msg("操作成功");
                window.location.href="/action/my/m/toMyJob?mid=a1&aid=ch13";  
            } else {
                layer.msg("保存失败");
            }
        })
    });
    function initialization(){
        $('.square input').iCheck({
            checkboxClass : 'icheckbox_square',
            radioClass : 'iradio_square',
            uncheck:true,
            increaseArea : '20%' // optional
        });
        //$('input').iCheck('uncheck');
        $("input[name='job-radio']").each(function() {
            if ($(this).attr("checked")) {
                var index = $(this).attr("jobindex");
                if (index == "now") {
                    $("#range_times").hide();
                    $("#start_date").hide();
                    $("#start_date1").hide();
                    $("#start_text").hide();
                    $("#end_date").hide();
                    $("#end_text").hide();
                    $("#byweek").hide();
                    $("#bymin").hide();
                    $("#prehour").hide();
                    $("#byhour").hide();
                    $("#bymonth").hide();
                } else if (index == 'once') {
                    $("#range_times").show();
                    $("#start_date").hide();
                    $("#start_date1").show();
                    $("#start_text").show();
                    $("#end_date").hide();
                    $("#end_text").hide();
                    $("#byweek").hide();
                    $("#bymin").hide();
                    $("#prehour").hide();
                    $("#byhour").hide();
                    $("#bymonth").hide();

                } else if (index == 'min') {
                    $("#range_times").show();
                    $("#start_date").show();
                    $("#start_date1").hide();
                    $("#start_text").show();
                    $("#end_date").show();
                    $("#end_text").show();
                    $("#bymin").show();
                    $("#byweek").hide();
                    $("#byhour").hide();
                    $("#bymonth").hide();
                    $("#prehour").hide();
                } else if (index == 'hour') {
                    $("#range_times").show();
                    $("#start_date").show();
                    $("#start_date1").hide();
                    $("#start_text").show();
                    $("#end_date").show();
                    $("#end_text").show();
                    $("#byweek").hide();
                    $("#bymin").hide();
                    $("#byhour").show();
                    $("#bymonth").hide();
                    $("#prehour").hide();
                } else if (index == 'day') {
                    $("#range_times").show();
                    $("#start_date").show();
                    $("#start_date1").hide();
                    $("#start_text").show();
                    $("#end_date").show();
                    $("#end_text").show();
                    $("#byweek").hide();
                    $("#bymin").hide();
                    $("#byhour").hide();
                    $("#bymonth").hide();
                    $("#prehour").show();
                } else if (index == 'week') {
                    $("#range_times").show();
                    $("#start_date").show();
                    $("#start_date1").hide();
                    $("#start_text").show();
                    $("#end_date").show();
                    $("#end_text").show();
                    $("#byweek").show();
                    $("#bymin").hide();
                    $("#byhour").hide();
                    $("#bymonth").hide();
                    $("#prehour").show();
                } else if (index == 'month') {
                    $("#range_times").show();
                    $("#start_date").show();
                    $("#start_date1").hide();
                    $("#start_text").show();
                    $("#end_date").show();
                    $("#end_text").show();
                    $("#bymin").hide();
                    $("#byweek").hide();
                    $("#byhour").hide();
                    $("#bymonth").show();
                    $("#prehour").hide();
                }
            }
        });
        $('input').on('ifChecked', function(event) {

            var index = $(this).attr("jobindex");
            if (index == "now") {
                $("#range_times").hide();
                $("#start_date").hide();
                $("#start_date1").hide();
                $("#start_text").hide();
                $("#end_date").hide();
                $("#end_text").hide();
                $("#byweek").hide();
                $("#bymin").hide();
                $("#prehour").hide();
                $("#byhour").hide();
                $("#bymonth").hide();
            } else if (index == 'once') {
                $("#range_times").show();
                $("#start_date").hide();
                $("#start_date1").show();
                $("#start_text").show();
                $("#end_date").hide();
                $("#end_text").hide();
                $("#byweek").hide();
                $("#byhour").hide();
                $("#bymin").hide();
                $("#bymonth").hide();
                $("#prehour").hide();
            } else if (index == 'min') {
                $("#range_times").show();
                $("#start_date").show();
                $("#start_date1").hide();
                $("#start_text").show();
                $("#end_date").show();
                $("#end_text").show();
                $("#bymin").show();
                $("#byweek").hide();
                $("#byhour").hide();
                $("#bymonth").hide();
                $("#prehour").hide();
            } else if (index == 'hour') {
                $("#range_times").show();
                $("#start_date").show();
                $("#start_date1").hide();
                $("#start_text").show();
                $("#end_date").show();
                $("#end_text").show();
                $("#bymin").hide();
                $("#byweek").hide();
                $("#byhour").show();
                $("#bymonth").hide();
                $("#prehour").hide();
            } else if (index == 'day') {
                $("#range_times").show();
                $("#start_date").show();
                $("#start_date1").hide();
                $("#start_text").show();
                $("#end_date").show();
                $("#end_text").show();
                $("#bymin").hide();
                $("#byweek").hide();
                $("#byhour").hide();
                $("#bymonth").hide();
                $("#prehour").show();
            } else if (index == 'week') {
                $("#range_times").show();
                $("#start_date").show();
                $("#start_date1").hide();
                $("#start_text").show();
                $("#end_date").show();
                $("#end_text").show();
                $("#bymin").hide();
                $("#byweek").show();
                $("#byhour").hide();
                $("#bymonth").hide();
                $("#prehour").show();
            } else if (index == 'month') {
                $("#range_times").show();
                $("#start_date").show();
                $("#start_text").show();
                $("#start_date1").hide();
                $("#end_date").show();
                $("#end_text").show();
                $("#byweek").hide();
                $("#bymin").hide();
                $("#byhour").hide();
                $("#bymonth").show();
                $("#prehour").hide();
            }
        });
    }
    //时间戳转为时间字符串
    function timetoString(time){
        var mon=time.getMonth() + 1;
        var day=time.getDate();
        var hour=time.getHours();
        var min=time.getMinutes();
        if(parseInt(mon)<10){
            mon="0"+mon;
        }
        if(parseInt(day)<10){
            day="0"+day;
        }
        if(parseInt(hour)<10){
            hour="0"+hour;
        }
        if(parseInt(min)<10){
            min="0"+min;
        }
        var date = (time.getFullYear()) + "-" +mon + "-" +day + " " + hour + ":" +min + ":" + (time.getSeconds());
        return date;
    }
</script>

</body>
</html>
