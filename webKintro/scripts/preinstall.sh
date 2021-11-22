echo 'NPM preinstall hook'
pwd

cd ../
./gradlew shared:compileProductionExecutableKotlinJs
./gradlew shared:jsPublicPackageJson

mkdir -p ./shared/build/dist
cp -r ./shared/build/compileSync/main/productionExecutable/kotlin/* ./shared/build/dist/
cp ./shared/build/tmp/jsPublicPackageJson/package.json ./shared/build/dist/package.json

mv ./shared/build/dist/kintro-shared.js ./shared/build/dist/index.js
mv ./shared/build/dist/kintro-shared.js.map ./shared/build/dist/index.js.map
mv ./shared/build/dist/kintro-shared.d.ts ./shared/build/dist/index.d.ts