//******************************************************************************
//* Program:   rptstrip.js
//* Description: 
//*            This program reads a text file containing a report
//*            strips off page titles, footers, and all column headers
//*            in the report body except those on the first page of
//*            the report.
//*
//*            Processing takes place using three files on the local
//*            server.
//*               - Original report file: a file containing the
//*                 report in its original, unaltered state.
//*               - Template file: a file containing the lines page
//*                 title, column heading, and page footer lines from
//*                 the first page of the report.
//*
//*                 These are the exact lines from the first page of
//*                 report and no modifications are necessary, even
//*                 though some data, like page number, may be specific
//*                 to the page. Multiple title, heading, and footing
//*                 lines may be specified.
//*
//* Special Considerations:
//*
//* - As much as possible this program exploits the asynchronous
//*   nature of Node.js. Events are used when it is necessary to
//*   synchronize on the completion of an asynchronous operation
//*   before continuing processing.
//*
//******************************************************************************

var Console = require('console').Console;
var fs = require('fs');
var stream = require('stream');

//------------------------------------------------------------------------------
// Object Prototypes
//------------------------------------------------------------------------------
// TODO: Create a NodeJS Module for the FileDef prototype and its functions
var FileDef =  {
   fileName : '',
   fileExists : false,
   fileError : false,
   fileRecord : '',
   fileRecPattern : '',
   fileRecLth : 0,
   fileTransform : null,
   contents : null,
   //
   // Function: openFile
   // Open the report file
   //
   setFileName : function (pFileName) {
      var _this = this;
      _this.fileName = typeof pFileName !== 'undefined' ? pFileName : '';
   },
   //
   // Function: openFile
   // Open the report file
   //
   verifyFileExists : function () {
      var _this = this;
      try {
         fs.statSync(_this.fileName);
         _this.fileExists = true;
         _this.fileTransform = new stream.Transform( { objectMode: true } );
         //console.info('openFile-fs.statSync: File found');
      } catch(err) {
         logger.error('openFile-fs.statSync: file ' + _this + ' does not exist');
         throw err;
      }
   },
   //
   // Function: fileTransform._transform
   // Read a line from the file
   //
   _transform : function (pSegment, pEncoding, pCompleted) {
      var data = pSegment.toString();
      if (this._lastLineData) {
         data = this._lastLineData + data;
      }

      var lines = data.split('\n');
      this._lastLineData = lines.splice(lines.length-1,1)[0];
      lines.forEach(this.push.bind(this));
      pCompleted();
   },
   //
   // Function: fileTransform._flush
   // Read the final line from the file
   //
   _flush : function(pCompleted) {
      if (this._lastLineData) {
         this.push(this._lastLineData);
      }
      this._lastLineData = null;
      pCompleted();
   }
};

var Patterns =  {
   count : 0,
   line : [],
   pattern : [],
   lth : []
};

//
// Function: processTemplateRecs
// Read the contents of the template file and save into the template object
//
var processTemplateRecs = function() {
   while (template.fileRecord = template.fileTransform.read()) {
      template.fileRecord = template.fileRecord.replace(/[\r\f]/,'');
      if (template.fileRecord.length > 0) {
         template.fileRecLth = template.fileRecord.length;
         template.contents.line[currentLineNo] = template.fileRecord;
         template.fileRecord = template.contents.line[currentLineNo];
         template.contents.lth[currentLineNo] = template.fileRecLth;
         template.contents.pattern[currentLineNo] = new RegExp(template.fileRecord.replace(/\^/g,'.'));
         template.contents.count = currentLineNo;
         logger.info('currentLineNo: ', currentLineNo,
                     ' lth: ', template.contents.lth[currentLineNo],
                     ' line: ', template.contents.line[currentLineNo]);
         logger.info('...template pattern: ', template.contents.pattern[currentLineNo]);
         currentLineNo++;
      }
   }
};

//
// Function: processReportRecs
// Read the contents of the report file and save into the report object
//
var processReportRecs = function() {
   var firstDetailProcessed = false;
   var keepLine = true;
   report.fileTransform
      .on('readable', function () {
         while (report.fileRecord = report.fileTransform.read()) {
            report.fileRecord = report.fileRecord.replace(/[\r\f]/,'');
            report.fileRecLth = report.fileRecord.length;
            logger.info('Processing fileRecord: ',report.fileRecord, ' lth: ', report.fileRecLth);
            // Skip blank lines
            if (report.fileRecord.length == 0) {
               logger.info('...Discard blank line', report.fileRecord);
               keepLine = true;
               continue;
            }
            // Only retain detail report lines.  Compare each line in the report file
            // against the template lines and discard any that match the title, header,
            // or footer lines contained in the template file.
            for (var idx = 0; idx <= template.contents.count; idx++) {
               var matchResult = report.fileRecord.match(template.contents.pattern[idx]);
               logger.info('Processing pattern:    ',template.contents.pattern[idx], ' lth: ', template.contents.lth[idx],
                           ' matchResult: ', matchResult, ' firstDetailProcessed: ', firstDetailProcessed);
               if (matchResult != null) {
                  logger.info('...Discard matching line. idx: ', idx);
                  keepLine = false;
                  break;
               }
            }
            // If the report line was not matched against one of the template lines and the first
            // detail line has yet to be written to the output file then the current report line
            // must be the first detail line.
            if (keepLine && !firstDetailProcessed) {
               firstDetailProcessed = true;
            }
            // If the report line was matched against one of the template lines and the first
            // detail line has yet to be encountered then the current report line is a title
            // or header line and should be written to the output file.
            if (!keepLine && !firstDetailProcessed) {
               keepLine = true;
            }
            // If the line is to be retained write it to the output file
            if (keepLine) {
               logger.info('...Keep line. idx: ', idx);
               newReport.write(report.fileRecord + '\n');
            }
            keepLine = true;
         }
      });
};

//------------------------------------------------------------------------------
// Initialization
//------------------------------------------------------------------------------

// Establish a custom Console object we'll use to log execution data to a log file
var output = fs.createWriteStream('./stdout.log', {highWaterMark: 32});
var errorOutput = fs.createWriteStream('./stderr.log', {highWaterMark: 32});
var logger = new Console(output, errorOutput);

// Read and parse the contents of the template file containing the page titles,
// column headers, and page footers.
var template = Object.create(FileDef);
template.contents = Object.create(Patterns);
template.setFileName('path to report template file goes here');
template.verifyFileExists();

var source = fs.createReadStream(template.fileName);
source.pipe(template.fileTransform);
template.fileTransform._transform = template._transform;
template.fileTransform._flush = template._flush;

// Read the lines of the template file, save lines and their attributes to template.lines,
// and calculate a hash for each line in the file. Blank lines are skipped.
var currentLineNo = 0;
template.fileTransform
   .on('error', function() {
      logger.info('Error encountered reading template file');
   })
   .on('readable', processTemplateRecs)
   .on('end', function() {
      logger.info('All template records read and processed');
   });

//------------------------------------------------------------------------------
// Main Processing
//------------------------------------------------------------------------------
var report = Object.create(FileDef);
report.setFileName('path to report file to process goes here',{highWaterMark: 32});
report.verifyFileExists();

var newReport = fs.createWriteStream('path to transformed report file goes here', {highWaterMark: 32});

// Read the lines of the template file, save lines and their attributes to template.lines,
// and calculate a hash for each line in the file.
source = fs.createReadStream(report.fileName);
source.pipe(report.fileTransform);
report.fileTransform._transform = report._transform;
report.fileTransform._flush = report._flush;

//
// Process the file containing the report and write only the first occurance
// of the headers and all detail lines to the new report file.
//
source
   .on('error', function() {
      logger.info('Error encountered reading report file');
   })
   .on('data', processReportRecs)
   .on('end', function() {
      logger.info('All report records read and processed');
   });

//------------------------------------------------------------------------------
// Program termination processing
//------------------------------------------------------------------------------
