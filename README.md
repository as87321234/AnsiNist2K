# iNistPack

Data Format for the Interchange of Fingerprint, Facial, &amp; Scar Mark &amp; Tattoo (SMT) Information based on the ANSI/NIST-ITL 1-2000 standard.

## Table of contents
* [General info](#general-info)
* [Technologies](#technologies)
* [Setup](#setup)
* [Features](#features)
* [Status](#status)
* [Inspiration](#inspiration)
* [Contact](#contact)

## General info

The iNistPack package is a java implementation of the ANSI/NIST-ITL 1-2000 standard. This java library contains methods  for reading, writing, and manipulating the content of ANSI/NIST data files. 

The java library is provided to parse ANSI/NIST files into memory, manipulate designated fields, and write the sequence back to file. This library is very similar to the an2ktool that can be used to work in batch mode. The library iNistPack works on logical data units where specified contents may be extracted, inserted, substituted, or deleted from the file.

The iNIstPack is not imposing any business validations around ANSI/NIST Packet content. It only guarantees the integrity of the file. Business validation will need to be implemented outside of the iNistPack.

The project inception was motivated by the need of having a non commercial Java implementation to create, read, write and update Electronic File Transfert file following the NIST format as describe in the following publication ["NIST Special Publication 500-245"](https://www.nist.gov/system/files/documents/itl/ansi/sp500-245-a16.pdf).

## Technologies
Java Implementation of the ANSI/NIST-ITI 1-2000

## Setup
Describe how to install / setup your local environement / add link to demo version.

## Code Examples
Show examples of usage:

```
INistPacket iNist = new INistPacket("filename path");
iNist.setItem("Value", "1.1.1.1.2");
iNist.findItem("1.1.1.1.2");
byte[] bNist = iNist.writeMem();
```

## Features
List of features 
* read, modify and write file following the ANSI/NIST-ITL 1-2000 standard

To-do list:
* 

Future Features:

* Validation framework based on JValidation

## Status
Project is: _in progress_

## Inspiration
The project was inspired by while reviewing the ["NIST Fingerprint Image Software (NFIS)" - User Guide](https://www.govinfo.gov/content/pkg/GOVPUB-C13-207080ee482017f6266cfac32d4efd16/pdf/GOVPUB-C13-207080ee482017f6266cfac32d4efd16.pdf) and more specically the AN2K2TXT tool.

## Contact
Created by [@as87321234]  email: as87321234@gmail.com  - feel free to contact me!

