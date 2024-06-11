import { mergeApplicationConfig, ApplicationConfig, importProvidersFrom } from '@angular/core';
import { provideServerRendering } from '@angular/platform-server';
import { appConfig } from './app.config';
import {MatToolbarModule} from '@angular/material/toolbar';

const serverConfig: ApplicationConfig = {
  providers: [
    provideServerRendering(), importProvidersFrom(MatToolbarModule)
  ]
};

export const config = mergeApplicationConfig(appConfig, serverConfig);
