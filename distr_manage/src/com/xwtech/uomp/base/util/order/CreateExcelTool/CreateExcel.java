package com.xwtech.uomp.base.util.order.CreateExcelTool;


import java.io.File;


import jxl.Workbook;
import jxl.format.Colour;
import jxl.format.UnderlineStyle;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.xwtech.uomp.base.pojo.order.ExcelBean;





/**
 * 根据ExcelBean生成excel文件
 * @author mars
 */
public class CreateExcel
{
	private static final Log log = LogFactory.getLog(CreateExcel.class);
	/**
	 * 构造方法
	 */
	public CreateExcel()
	{
	}
	public static boolean expordExcelAndSaveToFile(String fileSavePath, String fileName, ExcelBean excelBean) throws Exception
	{

	File dirPath = null;
	dirPath = new File(fileSavePath); //生成文件所在文件夹
	if (!dirPath.exists())
		dirPath.mkdirs();
	fileName = fileName + ".xls";

	File xlsFile = new File(fileSavePath + fileName);
	WritableWorkbook wbook = null;

	try
	{
		//WritableWorkbook wbook = Workbook.createWorkbook(os); //建立EXCLE文件
		wbook = Workbook.createWorkbook(xlsFile);

		//设置EXCEL标题
		WritableFont wfont = new WritableFont(WritableFont.ARIAL, 16, WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE, Colour.BLACK);
		WritableCellFormat wcfFC = new WritableCellFormat(wfont);
		wfont = new WritableFont(WritableFont.ARIAL, 14, WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE, Colour.BLACK);
		wcfFC = new WritableCellFormat(wfont);

		int sheetResultNum = 30000;//每页纪录数
		int curSheet = 1;//当前sheet页
		int cellCount = 0;//记录条数，从0开始

		Label wlabel;

		while ((cellCount % sheetResultNum == 0) && (cellCount < excelBean.getContent().length))
		{
			WritableSheet sheet = wbook.createSheet("第" + curSheet + "页", curSheet);
			curSheet++;
			//给每页设置标题
			for (int titleCount = 0; titleCount < excelBean.getTitleContent().length; titleCount++)
			{
				for (int j = 0; j < excelBean.getTitleContent()[titleCount].size(); j++)
				{
					//并且加粗标题行
					/*	if(titleCount==0){
					 wlabel = new Label(j+5, titleCount,
					 (excelBean.getTitleContent()[titleCount]).get(j)==null?"":(String)(excelBean.getTitleContent()[titleCount]).get(j),wcfFC);
					 sheet.addCell(wlabel);
					 }else{*/

					String str = (excelBean.getTitleContent()[titleCount]).get(j) == null ? ""
							: (String) (excelBean.getTitleContent()[titleCount]).get(j);

					wlabel = new Label(j, titleCount, str);
					sheet.addCell(wlabel);
					//	}
				}
			}
			//给每页填充内容
			while (cellCount < excelBean.getContent().length)
			{
				for (int j = 0; j < excelBean.getContent()[cellCount].size(); j++)
				{
					String str = (excelBean.getContent()[cellCount]).get(j) == null ? "" : (String) (excelBean.getContent()[cellCount]).get(j);
/*
					if (excelBean.getType().length > 0 && excelBean.getType()[j] == 1)
					{
						//如果当列格式被设置为保留小数点2位
						DecimalFormat nf = new DecimalFormat("0.00");
						str = nf.format((excelBean.getContent()[cellCount]).get(j));
					}
*/
					wlabel = new Label(j, excelBean.getTitleContent().length + (cellCount % sheetResultNum), str);
					sheet.addCell(wlabel);
				}
				cellCount++;
				if (cellCount % sheetResultNum == 0)
				{
					break;
				}
			}
		}

		wbook.write(); //写入文件
	}
	catch (Exception ex)
	{
		log.error("导出Excel文件到" + fileSavePath + fileName + "出错！", ex);
	}
	finally
	{
		try
		{
			if (wbook != null)
			{
				wbook.close();
			}
		}
		catch (Exception e)
		{
			log.debug("关闭Excel工作簿错误！", e);
		}
	}
	return true;
}}
