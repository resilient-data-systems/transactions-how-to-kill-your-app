#!/bin/bash
curl -sL --connect-timeout 1 http://localhost:9090/good/quote/$1
