#!/bin/sh

yarn remove kintro-shared
yarn add file:../shared/build/dist
yarn start
