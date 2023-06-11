using Clinic.Models;

namespace Clinic.DataAccess.Repository.IRepository
{
    public interface ITestRepository : IRepositoryAsync<Test>
    {
        void Update(Test test);
    }
}