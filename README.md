# iNistPack

Data Format for the Interchange of Fingerprint, Facial, &amp; Scar Mark &amp; Tattoo (SMT) Information

## Table of contents
* [General info](#general-info)
* [Technologies](#technologies)
* [Setup](#setup)
* [Features](#features)
* [Status](#status)
* [Inspiration](#inspiration)
* [Contact](#contact)

## General info

The iNistPack package is an java implementation of the ANSI/NIST-ITL 1-2000 standard. This java librairie contains methods  for reading, writing, and manipulating the contents of ANSI/NIST data files. 

The java librairy is provided to parse ANSI/NIST files into memory, manipulate designated fields, and write the sequence back to file. This library is very similar to the an2ktool that can be used to work in batch mode. The librarie iNistPack works on Logical data units where specified contents may be extracted, inserted, substituted, or deleted from the file.

The iNIstPack is not imposing any business validations around ANSI/NIST Packet content. It only guarantees the integrity of the file. Business validation will need to be implemented outside of the iNistPack.

The project inception was motivate by the need of having a non commercial Java implementation to create, read, write and update Electronic File Transfert file following the NIST format as describe in the following publication ["NIST Special Publication 500-245"](https://www.nist.gov/system/files/documents/itl/ansi/sp500-245-a16.pdf).



## Technologies

## Setup
Describe how to install / setup your local environement / add link to demo version.

## Code Examples
Show examples of usage:
`put-your-code-here`

## Features
List of features ready and TODOs for future development
* Awesome feature 1
* Awesome feature 2
* Awesome feature 3

To-do list:
* Wow improvement to be done 1
* Wow improvement to be done 2

Future Features:

* Validation framework based on JValidation

## Status
Project is: _in progress_, _finished_, _no longer continue_ and why?

## Inspiration
The project was inspired by while reviewing the ["NIST Fingerprint Image Software (NFIS)" - User Guide](https://www.govinfo.gov/content/pkg/GOVPUB-C13-207080ee482017f6266cfac32d4efd16/pdf/GOVPUB-C13-207080ee482017f6266cfac32d4efd16.pdf) and more specically the AN2K2TXT tool.

## Contact
Created by [@as87321234]  email: as87321234@gmail.com  - feel free to contact me!

