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
\global\let\@logoname\@empty
\newcommand{\setlogoname}[1]{\def\@logoname{#1}}
\let\logoname\setlogoname %
\newcommand{\show@logoname}{\@logoname}


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

% basic definition of the project environment. Will be expanded later in regards to the type of document.
\newenvironment{projdoc}{%
\begin{center}
  {\huge\textbf{\show@documentname, \show@projectname}}
  
  \vspace{20pt}
  \large\show@authorname
  
  \vspace{10pt}
  \large\textit{\show@revisioncurr}
  
  \vspace{10pt}
  \large\textbf{Abstract: }\normalsize\show@abstract
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
\RequirePackage{graphicx}
\RequirePackage{vHistory}

\RequirePackage{fancyhdr}
\fancypagestyle{firstpage}{%
  \headheight=76pt % This means that the logo can not be larger than 76pt (or 1inch)
  \headsep=150pt
  \fancyhf{} % clear all six fields
  \ifx\@logoname\@empty
    \relax
  \else
    \fancyhead[LE, LO]{\includegraphics[height=1in]{\show@logoname}}
  \fi %
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