import { Injectable } from '@angular/core';
import { Observable, Subject, BehaviorSubject } from 'rxjs';
import { HttpWrapperService } from '../http-wrapper/http-wrapper.service';
import { User } from './user';
import { UserRole } from './user-role';
import { map, tap } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class UserInformationService {
  private loggedUser: User;
  private loggedInUser$: Subject<User | void> = new BehaviorSubject(null);
  private space = '2021a.vitalyg1';
  constructor(private httpWrapper: HttpWrapperService) { }

  public login(userMail: any): Observable<User> {
    return this.httpWrapper.getWithParams(
      `http://localhost:8081/dts/users/login/${this.space}/#{0}#`, [userMail.email])
      .pipe(map((res: any) => {
        let user: User = {
          avatar: res.avatar,
          userName: res.username,
          userId: res.userId,
          role: res.role
        } as User;
        return user;
      }), tap(loggedUser => this.loggedInUser$.next(loggedUser),
        tap((loggedUser: User) => this.loggedUser = loggedUser)));
  }

  public create(user: User): Observable<User> {
    return this.httpWrapper.post(
      `http://localhost:8081/dts/users`,
      JSON.parse(
        JSON.stringify({
          username: user.userName,
          avatar: user.avatar,
          email: user.userId.email,
          //role: UserRole.PLAYER
        })
      )).pipe(map((res: User) => {
        return res;
      }), tap(loggedUser => this.loggedInUser$.next(loggedUser)));
  }

  public updateUser(userName: string, avatar: string, user: User): Observable<User> {
    return this.httpWrapper.putWithParams(
      `http://localhost:8081/dts/users/#{0}#/#{1}#`, [userName, this.space],
      JSON.parse(
        JSON.stringify({
          username: user.userName,
          avatar: user.avatar,
          email: user.userId.email,
          //role: UserRole.PLAYER
        })
      )
    ).pipe(map((res: User) => {
      return res;
    }), tap(loggedUser => this.loggedInUser$.next(loggedUser)));
  }

  public getLoggedInUser(): Observable<User | void> {
    return this.loggedInUser$.asObservable();
  }

  public getLoggedUser() {
    return this.loggedUser;
  }
}
