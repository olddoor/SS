/******************************************************************************
  Crossday Discuz! Board - Common Modules for Discuz!
  Copyright 2001-2005 Comsenz Technology Ltd (http://www.comsenz.com)
*******************************************************************************/

var sPop = null;
var postSubmited = false;

document.write("<style type='text/css'id='defaultPopStyle'>");
document.write(".cPopText { font-family: Tahoma, Verdana; background-color: #FFFFCC; border: 1px #000000 solid; font-size: 12px; padding-right: 4px; padding-left: 4px; height: 20px; padding-top: 2px; padding-bottom: 2px; visibility: hidden; filter: Alpha(Opacity=80)}");

document.write("</style>");
document.write("<div id='popLayer' style='position:absolute;z-index:1000' class='cPopText'></div>");
document.onmouseover=showpopuptext;
