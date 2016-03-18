#!/bin/bash

if [ 'bbuser' != `whoami` ] ; then
  echo ERROR this script must be run by user 'bbuser'
  exit;
fi

CMD="/usr/local/blackboard/tools/admin/B2Manager.sh"

# WeatherModule
$CMD -r bb-1027721203705

# Time
$CMD -r time-time-plgnhndl

# SafeAssign
$CMD -r mdb-sa

# SCORM Engine
$CMD -r scor-scormengine

# Textbook
$CMD -r Bb-textbook

# Open Standards Content Player
$CMD -r bb-cntplayer

# Mashup - YouTube
$CMD -r bb-mashups-youtube

# Mashup - SlideShare
$CMD -r bb-mashups-slideshare

# Mashup - Flickr
$CMD -r bb-mashups-flickr

# Maps
$CMD -r bb-1027008845953

# GoogleModule
$CMD -r bb-1027720613048

$CMD -r bb-data-integration-ss-xml
#$CMD -r bb-data-integration-flatfile
$CMD -r bb-data-integration-ims-xml
$CMD -r bb-data-integration-lis
$CMD -r bb-data-integration-lis-final

# Security Management: Cookie Disclosure
$CMD -r bb-cookie-disclosure

# Zap2itModules
$CMD -r bb-1027979249287

# Course Packages - Vista/CE8 Import
$CMD -r bb-cx-ext-vista-plgnhndl

# Course Packages - Common Cartridge
$CMD -r bb-ims-cc 

# Course Packages - CE4 Import
$CMD -r bb-cx-ext-ce4-plgnhndl

# Course Packages - ANGEL Import 
$CMD -r bb-cx-ext-angel-plgnhndl

$CMD -r bb-auth-provider-shibboleth
$CMD -r bb-auth-provider-ldap
$CMD -r bb-auth-provider-cas

# Astrology
$CMD -r astr-1025622397760

# Blackboard Mobile Web Services Building Block
$CMD -r Bb-mobile

# HETemplateModules
$CMD -r bb-1027954530152

# xpLor Connector
$CMD -r bb-xplor-connector

# Social Profiles and Tools
$CMD -r bb-social-connector

# Grade Journey depends on self/peer assessment...
#$CMD -r bb-selfpeer

# Achievements
$CMD -r bb-achievements

# Financial Aid
$CMD -r bb-financial-aid

# Redis Cache
$CMD -r bb-redis-cache

# Student Goal Performance Dashboard
$CMD -r bb-goal

