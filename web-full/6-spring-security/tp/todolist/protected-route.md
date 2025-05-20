## ✅ Objectif : Bloquer l’accès à certaines routes si l’utilisateur **n’a pas de JWT valide**

---

### 🔧 Étape 1 : Générer un guard

```bash
ng generate guard auth/auth
```

---

### 📄 `auth.guard.ts` (exemple avec un JWT)

```ts
import { Injectable } from '@angular/core';
import { CanActivate, Router } from '@angular/router';
import { AuthService } from './auth.service'; // service qui gère le token

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {

  constructor(private authService: AuthService, private router: Router) {}

  canActivate(): boolean {
    if (this.authService.isAuthenticated()) {
      return true;
    } else {
      this.router.navigate(['/login']); // redirige vers login
      return false;
    }
  }
}
```

---

### 📄 `auth.service.ts` (simplifié)

```ts
@Injectable({
  providedIn: 'root'
})
export class AuthService {
  getToken(): string | null {
    return localStorage.getItem('jwt_token');
  }

  isAuthenticated(): boolean {
    const token = this.getToken();
    // Optionnel : tu peux aussi vérifier sa validité / expiration
    return !!token;
  }
}
```

---

### 📄 Étape 3 : Appliquer le guard dans le routeur

```ts
const routes: Routes = [
  { path: 'dashboard', component: DashboardComponent, canActivate: [AuthGuard] },
  { path: 'login', component: LoginComponent },
  { path: '**', redirectTo: 'login' }
];
```

---

### 🛡 Résultat :

* Si l’utilisateur **a un token JWT**, il peut accéder à `/dashboard`.
* Sinon, il est automatiquement redirigé vers `/login`.

---

## ✅ Bonus : Gestion du token expiré

Tu peux renforcer `isAuthenticated()` avec la lib [jwt-decode](https://www.npmjs.com/package/jwt-decode) pour vérifier l’expiration :

```bash
npm install jwt-decode
```

```ts
import jwtDecode from 'jwt-decode';

isAuthenticated(): boolean {
  const token = this.getToken();
  if (!token) return false;

  try {
    const decoded: any = jwtDecode(token);
    const exp = decoded.exp * 1000; // ms
    return Date.now() < exp;
  } catch (e) {
    return false;
  }
}
```


