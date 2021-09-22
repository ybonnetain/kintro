#!/bin/sh

ENV=$1

if [[ $ENV == *"en"* ]]; then
  IP=$(ipconfig getifaddr $ENV)
  echo "setenv: $ENV -> $IP"
  cat <<EOF >./shared/src/commonMain/kotlin/dev/ybonnetain/kintro/Configuration.kt
// GENERATED FILE
package dev.ybonnetain.kintro
object Configuration {
    val host  = "$IP"
    val scheme = "http"
    val mock = false
}
EOF

elif [ $ENV = "mock" ]; then   
    cat <<EOF >./shared/src/commonMain/kotlin/dev/ybonnetain/kintro/Configuration.kt
// GENERATED FILE
package dev.ybonnetain.kintro
object Configuration {
    val host  = "none"
    val scheme = "none"
    val mock = true
}
EOF

  elif [ $ENV = "local" ]; then   
    cat <<EOF >./shared/src/commonMain/kotlin/dev/ybonnetain/kintro/Configuration.kt
// GENERATED FILE
package dev.ybonnetain.kintro
object Configuration {
    val host  = "localhost:3001"
    val scheme = "http"
    val mock = false
}
EOF

  elif [ $ENV = "prd" ]; then  
  cat <<EOF >./shared/src/commonMain/kotlin/dev/ybonnetain/kintro/Configuration.kt
// GENERATED FILE
package dev.ybonnetain.kintro
object Configuration {
    val host  = "jsonplaceholder.typicode.com"
    val scheme = "https"
    val mock = false
}
EOF

fi
