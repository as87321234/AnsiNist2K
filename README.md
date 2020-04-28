# AnsiNist2K - Traditional Encoding

Data Format for the Interchange of Fingerprint, Facial, &amp; Scar Mark &amp; Tattoo (SMT) Information based on the ANSI/NIST-ITL 1-2000 standard.

## Table of contents
* [General info](#general-info)
* [Technologies](#technologies)
* [Features](#features)
* [JUnit](#JUnit)
* [Status](#status)
* [Inspiration](#inspiration)
* [Contact](#contact)

## General info

The AnsiNist2K package is a java implementation of the ANSI/NIST-ITL 1-2000 standard. This java library contains methods for reading, writing, and manipulating the content of ANSI/NIST data files. 

The java library is provided to parse ANSI/NIST files into memory, manipulate designated fields, and write the sequence back to file. This library is very similar to the an2ktool that can be used to work in batch mode. The library AnsiNist2K works on logical data units where specified contents may be extracted, inserted, substituted, or deleted from the file.

The AnsiNist2K is not imposing any business validations around ANSI/NIST Packet content. It only guarantees the integrity of the file while being read, manipulated, and written back to file. This makes AnsiNist2K business neutral and enable your project to leverage ANSI/NIST Standard file with your own ICD definition.

The AnsiNist2K is perfect for project with limited number of transactions types and simple validation set of rules, to be embeeded into mobile application requiring to produce ANSI NIST file or used with MVC application to offer basic ANSI NIST transformations that can be later leverage by a performance testing framework such as Load Runner, JMeter to inject transactions into an Automated Biometric Identification System (ABIS).

For complexe project requiring high degree of validation or complexe validation rules, it is recommanded to use a commercial library that leverage a thorought tested validation framework.

The project inception was motivated by the need of having a non-commercial Java implementation to create, read, write and update Electronic File Transfert file following the NIST format as described in the following publication ["NIST Special Publication 500-245"](https://www.nist.gov/system/files/documents/itl/ansi/sp500-245-a16.pdf).

## Technologies
Java Implementation of the ANSI/NIST-ITI 1-2000

## Code Examples
Show examples of usage:

```
AnsiNist2K nist = new AnsiNist2K("filename path");
nist.setItem("Value", "1.1.1.1.2");
nist.findItem("1.1.1.1.2");
byte[] bNist = nist.writeMem();
```

## Features
List of features 
* read, modify and write files following the ANSI/NIST-ITL 1-2000 standard using ISO-8859-1 file encoding

To-do list:
* Support Base64 file ouput
* Support UTF java VM file encoding
* Support all java VM  -Dfile.encoding


Future Features:
* Validation framework based on JValidation
* Support Simple Record Hashing based on a shared secret
* Support Record Hashing based on X509 Certificate
* Support Field Encryption based on X509 Certificate
* Extract record type 10 as JPEG
* Extract record type 14 WSQ fingerprint

## JUnit

To-do list:

* Verify that support for file encoding different than ISO-8859-1
* Verify that AnsiNist2K can read and write AN2011_Sample_Data Traditional Encoding about 96 different tests
* Verify that AnsiNist2K can read and write AN2013_Sample_Data Traditional Encoding about 12 different tests

## Known Limitations

* The AnsiNist2K Project works well if the Java VM run with -Dfile.encoding=ISO_8859_1 environment property. It is understood that this may not be suitable for all projects. Future releases will ensure that all file encoding are being supported.

## Status
Project is: _in progress_

## Inspiration
The project was inspired while reviewing the ["NIST Fingerprint Image Software (NFIS)" - User Guide](https://www.govinfo.gov/content/pkg/GOVPUB-C13-207080ee482017f6266cfac32d4efd16/pdf/GOVPUB-C13-207080ee482017f6266cfac32d4efd16.pdf) and more specifically the AN2K2TXT tool.

## Contact
Created by [@as87321234]  email: as87321234@gmail.com - feel free to contact me!

_Retired from the corporate world but alway looking for a good challenge_ 
