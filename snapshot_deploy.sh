#!/usr/bin/env bash
if [ "$TRAVIS_BRANCH" = 'master' ] && [ "$TRAVIS_PULL_REQUEST" == 'false' ]; then
    export GPG_TTY=tty
  ./gradlew uploadArchives -PossrhUsername=${OSSRH_USERNAME} -PossrhPassword=${OSSRH_PASSWORD} -Psigning.keyId=${GPG_KEY_ID} -Psigning.password=${GPG_KEY_PASSPHRASE}
fi
