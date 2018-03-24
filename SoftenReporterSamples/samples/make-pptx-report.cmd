java -cp .;lib/* com.softenware.report.SoftenReporterProcess -template template\Template-Sample-A.pptx -in data\InputData.object -out result\Report-Result-fromObjectInput.pptx

java -cp .;lib/* com.softenware.report.SoftenReporterProcess -template template\Template-Sample-A.pptx -in data\InputData.kson -data kson -out result\Report-Result-fromKsonInput.pptx

java -cp .;lib/* com.softenware.report.SoftenReporterProcess -template template\Template-Sample-A.pptx -in data\InputData.json -data json -out result\Report-Result-fromJsonInput.pptx

java -cp .;lib/* com.softenware.report.SoftenReporterProcess -template template\Template-Sample-A.pptx -in data\InputData.xlsx -data xlsx -out result\Report-Result-fromXlsxInput.pptx -date yy,MM/dd