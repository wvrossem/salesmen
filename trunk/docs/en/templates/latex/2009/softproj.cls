% softproj.sty
%  Any document for Salesmen Project
% 2009-Oct-30 Patrick Provinciael patrick.provinciael@gmail.com
% Based upon the mins.cls, created by Jim Hefferon, http://www.tug.org/pracjourn/2005-4/hefferon/
% The idea is to expand this, in such a way that the defined documentname will affect the documentstyle
%\vhEntry{0.5}{25-10-2009}{Patrick}{First Draft}
%\vhEntry{1.0}{30-10-2009}{Patrick}{First Finished Version}
%\vhEntry{1.1}{11-11-2009}{Patrick}{Changed base Class to report}
%\vhEntry{1.3}{19-11-2009}{Patrick}{Addition of Revision History}


% --- Class structure: identification part
% ---
\ProvidesClass{softproj}[2009/10/30 Documentclass for any document for a Software Project]
\NeedsTeXFormat{LaTeX2e}

% --- Class structure: initial code part
% ---

% Which Project?
\def\@projectname{}
\newcommand{\setprojectname}[1]{\def\@projectname{#1}}
\newcommand{\show@projectname}{\@projectname}

% Projectmembers? 
\def\@members{None}
\newcommand{\setmembers}[1]{\def\@members{#1}}
\newcommand{\show@members}{\@members}
% what role do they have (e.g., manager)?
\newcommand{\role}[2]{#1~(#2)}
\newcommand{\manager}[1]{\role{#1}{Manager}}

% What kind of Document? 
\def\@documentname{}
\newcommand{\setdocumentname}[1]{\def\@documentname{#1}}
\newcommand{\show@documentname}{\@documentname}

% Who wrote it?
\def\@authorname{}
\newcommand{\setauthorname}[1]{\def\@authorname{#1}}
\newcommand{\show@authorname}{\@authorname}

% What is the document about?
\def\@abstract{}
\newcommand{\setabstract}[1]{\def\@abstract{#1}}
\newcommand{\show@abstract}{\@abstract}

% Which Revision is it? revisionhist currently not in use
\def\@revisioncurr{\today, None}
\def\@revisionhist{\today, None}
\newcommand{\setrevisionhist}[1]{\def\@revisionhist{#1}}
\newcommand{\show@revisionhist}{\@revisionhist}
\newcommand{\setrevisioncurr}[1]{\def\@revisioncurr{#1}}
\newcommand{\show@revisioncurr}{\@revisioncurr}

% The name of the logo of the project, if any?
\global\let\@projectlogo\@empty
\newcommand{\setprojectlogo}[1]{\def\@projectlogo{#1}}
\let\logoname\setprojectlogo %
\newcommand{\show@projectlogo}{\@projectlogo}

% The name of the logo of the company, if any?
\global\let\@companylogo\@empty
\newcommand{\setcompanylogo}[1]{\def\@companylogo{#1}}
\let\logoname\setcompanylogo %
\newcommand{\show@companylogo}{\@companylogo}


% --- Class structure: declaration of options part
% ---
% This class extends the article class
% Read all the documentclass options; pass them to article, unless the file
% named "<currentoption>.opt" exists, in which case it is loaded
%\DeclareOption*{\InputIfFileExists{\CurrentOption.min}{}{%
%    \PassOptionsToClass{\CurrentOption}{article}}
%\DeclareOption*{\PassOptionsToClass{\CurrentOption}{article}}
\DeclareOption*{\InputIfFileExists{\CurrentOption.opt}{}{%
    \PassOptionsToClass{\CurrentOption}{report}}}

% --- Class structure: execution of options part
% ---
\ProcessOptions \relax

% --- Class structure: declaration of options part
% ---
\LoadClass{report}

% --- Class structure: main code part
% ---

\RequirePackage{graphicx}
\RequirePackage{vhistory}
\graphicspath{{../../../common/img/}{img/}}

% basic definition of the project environment. Will be expanded later in regards to the type of document.
\newenvironment{projdoc}{%
\begin{center}
  \ifx\@logoname\@empty
    \relax
  \else
    \includegraphics[height=2in]{\show@projectlogo}
    \vspace{-2in}
  \fi %
  
  \addvspace{70mm}
  \Large{\show@projectname}\\
  \rule{148mm}{1pt}\\
  \vspace{8pt}
  \LARGE\textbf{\show@documentname}\\
  \rule{148mm}{1pt}
    
  \addvspace{5mm}
  \large{\textit{author(s):} \show@authorname}
  
  \vspace{10mm}
  \ifx\@logoname\@empty
    \relax
  \else
    \includegraphics[height=1.5in]{\show@companylogo}
    \vspace{-1.5in}
  \fi %
  
  \addvspace{60mm}
  \large\textbf{Abstract: }\normalsize\show@abstract
  

  \addvspace{15mm}
  \large\textit{\show@revisioncurr}
\end{center}
\newpage
\setcounter{page}{1}
\show@revisionhist
\newpage
\tableofcontents
\newpage
}{%
}

% Page layout
\RequirePackage[left=1.3in,right=1.3in,top=1.2in,bottom=1.3in]{geometry}

\RequirePackage{fancyhdr}
\fancypagestyle{firstpage}{%
  \fancyhf{} % clear all six fields
  \renewcommand{\headrulewidth}{0pt}
  \renewcommand{\footrulewidth}{0pt}
}
\fancypagestyle{followingpage}{%
  \headheight=12pt
  \fancyhf{} % clear all six fields
  \fancyhead[CE,CO]{\show@documentname}
  \fancyhead[RE,RO]{\show@projectname}
  \fancyfoot[CE,CO]{\thepage}
  \renewcommand{\headrulewidth}{1pt}
  \renewcommand{\footrulewidth}{0pt}
}

\pagestyle{followingpage}
\AtBeginDocument{\thispagestyle{firstpage}}

\setcounter{tocdepth}{2}